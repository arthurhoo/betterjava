package com.huhao.code.advancejava.scheduler;

import com.huhao.code.advancejava.utils.MathUtils;

import java.math.RoundingMode;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by huhao on 2017/1/29.
 */
public class MockTask extends AbstractTask{

    private Long order;

    public MockTask(Long order, Long period){
        this.order = order;
        this.period = period;
    }

    @Override
    public void run() {
        try{
            System.out.println("Thread Name="+Thread.currentThread().getName());
            this.isWorked = true;

        }catch (Throwable throwable){
            System.err.println("Task error."+ throwable.toString());
        }
    }

    public void buildTaskExecuteTime(Integer slot) {
        Long secondSpan = MathUtils.divide(period, SECONDS.toMillis(1L), RoundingMode.HALF_UP);

        //设置挂载时间
        this.nextInSeconds = secondSpan;
        this.nextInMinutes = secondSpan / 60;
        if (nextInMinutes > 0) {
            this.nextInSeconds = secondSpan - nextInMinutes * 60;
            if (nextInMinutes > 60) {
                nextInHour = nextInMinutes / 60;
                this.nextInMinutes = nextInMinutes - nextInHour * 60;
                if (nextInHour > 24) {
                    nextInDay = nextInHour / 24;
                    this.nextInHour = nextInHour - nextInDay * 24;
                }

            }
        }

        if (slot >= 0 && slot < 60) {
            this.nextInSeconds = slot + this.nextInSeconds;
        }
    }

    /**
     * 更新任务执行时间
     */
    private void updateExecuteTime() {

        synchronized (MockTask.class) {
            this.nextInMinutes = this.nextInMinutes - 1L;
            if (this.nextInMinutes < 0L) {
                if (this.nextInDay > 0L || this.nextInHour > 0L) {
                    this.nextInHour = this.nextInHour - 1L;
                    this.nextInMinutes = 59L;
                    if (nextInHour < 0L) {
                        if (this.nextInDay > 0L) {
                            this.nextInDay = this.nextInDay - 1;
                            this.nextInHour = 23L;
                            if (nextInDay < 0L) {
                                nextInDay = 0L;
                            }
                        } else {
                            this.nextInHour = 0L;
                        }

                    }
                } else {
                    this.nextInMinutes = 0L;
                }

            }
        }

    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Override
    public Object clone(){
        MockTask mockTask = null;
        try {
            mockTask = (MockTask)super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }
        return mockTask;
    }
}
