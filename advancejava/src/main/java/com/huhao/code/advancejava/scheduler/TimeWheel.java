/*-
 * #%L
 * guardian-core
 * %%
 * Copyright (C) 2016 - 2017 Ant Financial Services Group
 * %%
 * This software is developed by Ant Financial Services Group.This software and all the relevant information, 
 * including but not limited to any signs, images, photographs, animations, text, interface design, 
 * audios and videos, and printed materials, are protected by copyright laws and other intellectual property laws and treaties. 
 * The use of this software shall abide by the laws and regulations as well as Software Installation License Agreement/Software 
 * Use Agreement updated from time to time. Without authorization from Ant Financial Services Group , 
 * no one may conduct the following actions: 
 * 
 * 1) reproduce, spread, present, set up a mirror of, upload, download this software; 
 * 
 * 2) reverse engineer, decompile the source code of this software or try to find the source code in any other ways; 
 * 
 * 3) modify, translate and adapt this software, or develop derivative products, works, and services based on this software; 
 * 
 * 4) distribute, lease, rent, sub-license, demise or transfer any rights in relation to this software, 
 * or authorize the reproduction of this software on other computers.
 * #L%
 */

package com.huhao.code.advancejava.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 秒级时间轮结构定时器 Created by huhao on 2017/1/18.
 */
public class TimeWheel implements AbstractScheduler {

    private static ArrayList<ArrayList<AbstractTask>> dialInSeconds            = new ArrayList<ArrayList<AbstractTask>>(60);

    private static AtomicInteger                      secondPointer            = new AtomicInteger(0);

    private ScheduledExecutorService                  timeDriver;

    private ThreadPoolExecutor                        workThreads;

    private ExecutorService                           workProducerThread;

    private ExecutorService                           workConsumerThread;

    static final Integer                              DEFAULT_WORKTHREAD_COUNT = 10;

    private volatile boolean                          isRunning                = false;

    private volatile boolean                          startConsumer            = false;

    private BlockingDeque<AbstractTask>               tasks                    = new LinkedBlockingDeque<AbstractTask>();

    @Override
    public TimeWheel build() {

        for (int i = 0; i < 60; i++) {
            dialInSeconds.add(new ArrayList<AbstractTask>());
        }
        workThreads = (ThreadPoolExecutor) Executors.newFixedThreadPool(DEFAULT_WORKTHREAD_COUNT, new ThreadFactory() {

            final AtomicLong count = new AtomicLong(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(new ThreadGroup("TimeWheel"), r, "Worker-" + count.getAndIncrement());
            }
        });

        timeDriver = Executors.newScheduledThreadPool(1, new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(new ThreadGroup("TimeWheel"), r, "TimeWheelDriveThread");
            }
        });
        return this;
    }

    @Override
    public boolean start() {
        if (timeDriver == null) {
            throw new IllegalStateException("start@TimeWheel, timeDriver thread is not been created.");
        }

        if (timeDriver != null && !isRunning) {
            try {
                timeDriver.scheduleAtFixedRate(new TimeDriverThread(), 0, 1000, TimeUnit.MILLISECONDS);

            } catch (Throwable throwable) {
                System.out.println("start time driver error. " + throwable);
            }
            isRunning = true;

            workProducerThread = Executors.newSingleThreadExecutor(new ThreadFactory() {

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(new ThreadGroup("Guardian"), r, "WorkProducer");
                }
            });

            workConsumerThread = Executors.newSingleThreadExecutor(new ThreadFactory() {

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(new ThreadGroup("Guardian"), r, "WorkConsumer");
                }
            });
        }
        return isRunning;
    }

    @Override
    public boolean stop() {
        if (isRunning) {
            isRunning = false;
            timeDriver.shutdown();
            workThreads.shutdown();
            workProducerThread.shutdown();
            workConsumerThread.shutdown();
        }
        return !isRunning;
    }

    /**
     * 监听日志打印任务生产者
     */
    private void produceLogWork(Integer pointer) throws Throwable {

        List<AbstractTask> taskInHocker = dialInSeconds.get(pointer);
        if (taskInHocker != null && !taskInHocker.isEmpty()) {
            for (AbstractTask task : taskInHocker) {
                tasks.add(task);

            }
            if(!startConsumer){
                workConsumerThread.execute(new WorkConsumer());
            }
            dialInSeconds.set(pointer, null);
        }

    }

    class WorkProducer implements Runnable {

        private Integer dialPointor;

        public Integer getDialPointor() {
            return dialPointor;
        }

        public void setDialPointor(Integer dialPointor) {
            this.dialPointor = dialPointor;
        }

        WorkProducer(Integer dialPointor){
            this.dialPointor = dialPointor;
        }

        @Override
        public void run() {
            if (isRunning) {
                try {
                    produceLogWork(this.dialPointor);
                } catch (Throwable e) {
                    System.err.println("workproducer error." + e.getMessage());
                    System.err.println("workproducer error." + e.getStackTrace());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 打印人户消费者
     * 
     * @return
     */
    class WorkConsumer implements Runnable {

        private void executeWork() throws Throwable {

            if (!isRunning()) {
                System.out.println("TimeWheel is not running!");
                return;
            }
            System.out.println("workconsumer is running. Thread=" + Thread.currentThread().getName() + " , tasks size="
                               + tasks.size());
            MockTask task = null;

            task = (MockTask) tasks.take();
            workThreads.execute(task);
            reregisterTask(task);

        }

        @Override
        public void run() {
            while (isRunning()) {
                try {
                    executeWork();
                } catch (Throwable e) {
                    System.err.println("Consumer error." + e.toString());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注册任务
     * 
     * @param task
     * @return
     */
    public boolean registerTask(AbstractTask task) {

        try {
            int secondIndex = secondPointer.get();

            task.buildTaskExecuteTime(secondIndex);

            int hockIndex = task.getNextInSeconds().intValue();

            hockIndex = hockIndex % 60;

            ArrayList<AbstractTask> timeTasks = dialInSeconds.get(hockIndex);
            if (timeTasks == null) {
                timeTasks = new ArrayList<AbstractTask>();
                dialInSeconds.add(hockIndex, timeTasks);
            }
            timeTasks.add(task);
            System.out.println("task order=" + ((MockTask) task).getOrder() + " ,secondIndex=" + hockIndex);

        } catch (Throwable throwable) {
            System.out.println("registerTask@TimeWheel, register log task error. " + throwable.getMessage());
            return false;
        }

        return true;

    }

    /**
     * 处理未到时间打印的任务
     * 
     * @param task
     * @return
     */
    public boolean reregisterTask(AbstractTask task) throws Exception {
        try {

            int hockIndex = task.getNextInSeconds().intValue();

            if (task.isWorked) {
                int secondIndex = secondPointer.get();

                task.buildTaskExecuteTime(secondIndex);

                hockIndex = hockIndex % 60;
            }

            ArrayList<AbstractTask> timeTasks = dialInSeconds.get(hockIndex);
            if (timeTasks == null) {
                timeTasks = new ArrayList<AbstractTask>();
                dialInSeconds.add(hockIndex, timeTasks);
            }
            timeTasks.add(task);
            System.out.println("reregister task order=" + ((MockTask) task).getOrder() + " ,secondIndex=" + hockIndex);

        } catch (Throwable throwable) {
            System.out.println("registerTask@TimeWheel, register log task error. " + throwable);
            return false;
        }

        return true;
    }

    @Override
    public boolean unregistTask(AbstractTask task) {
        Long index = task.getNextInSeconds();
        List<AbstractTask> tasks = dialInSeconds.get(index.intValue());
        if (tasks == null || !tasks.contains(task)) {
            return true;
        }
        return tasks.remove(task);
    }

    @Override
    public void destroy() {
        dialInSeconds.clear();
        secondPointer.set(0);
        stop();
        timeDriver = null;
        workThreads = null;
        workConsumerThread = null;
        workProducerThread = null;
    }

    /**
     * 驱动时钟前进的定时线程
     */
    public class TimeDriverThread implements Runnable {

        public TimeDriverThread(){
        }

        @Override
        public void run() {
            synchronized (TimeDriverThread.class) {

                try {
                    System.out.println(Thread.currentThread().getName());
                    Integer pointer = secondPointer.get();
                    System.out.println("current seconds:" + secondPointer.get());
                    workProducerThread.execute(new WorkProducer(pointer));
                    if (secondPointer.incrementAndGet() >= 60) {
                        secondPointer.set(0);
                    }
                } catch (Throwable throwable) {
                    System.err.println("timeDriver error." + throwable.getMessage());
                }

            }

        }
    }

    public static ArrayList<ArrayList<AbstractTask>> getDialInSeconds() {
        return dialInSeconds;
    }

    public static void setDialInSeconds(ArrayList<ArrayList<AbstractTask>> dialInSeconds) {
        TimeWheel.dialInSeconds = dialInSeconds;
    }

    public static AtomicInteger getSecondPointer() {
        return secondPointer;
    }

    public static void setSecondPointer(AtomicInteger secondPointer) {
        TimeWheel.secondPointer = secondPointer;
    }

    public ScheduledExecutorService getTimeDriver() {
        return timeDriver;
    }

    public void setTimeDriver(ScheduledExecutorService timeDriver) {
        this.timeDriver = timeDriver;
    }

    public ThreadPoolExecutor getWorkThreads() {
        return workThreads;
    }

    public void setWorkThreads(ThreadPoolExecutor workThreads) {
        this.workThreads = workThreads;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public BlockingDeque<AbstractTask> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingDeque<AbstractTask> tasks) {
        this.tasks = tasks;
    }
}
