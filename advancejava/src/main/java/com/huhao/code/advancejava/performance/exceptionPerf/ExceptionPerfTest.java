package com.huhao.code.advancejava.performance.exceptionPerf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huhao on 16/10/14.
 */
public class ExceptionPerfTest {
    private static final int Loop = 10000000;

    private static final int THREADCOUNT=10;

    private static final List<Long> newNormalObjectTimes = new ArrayList<Long>();

    private static final List<Long> newGuardianBizExceptionTimes = new ArrayList<Long>();

    private static final List<Long> newGuardianBizExtExceptionTimes = new ArrayList<Long>();

    private static final ExecutorService POOL = Executors.newFixedThreadPool(30);

    public static void main(String[] args) throws Exception{
        List<Callable<Boolean>> all = new ArrayList<Callable<Boolean>>();
        all.addAll(tasks(new CreateNormalObject()));
        all.addAll(tasks(new CreatGuardianBizException()));
        all.addAll(tasks(new CreatGuardianBizExtException()));

        POOL.invokeAll(all);

        System.out.println("normal object:"+total(newNormalObjectTimes));
        System.out.println("GuardianBizException:"+total(newGuardianBizExceptionTimes));
        System.out.println("GuardianBizExtException:"+total(newGuardianBizExtExceptionTimes));

        POOL.shutdown();

    }

    private static List<Callable<Boolean>> tasks(Callable<Boolean> c){
        List<Callable<Boolean>> list = new ArrayList<Callable<Boolean>>();
        for(int i=0;i<THREADCOUNT;i++){
            list.add(c);
        }
        return list;
    }

    private static long total(List<Long> list){
        long sum = 0;
        for(Long v:list){
            sum+=v;
        }
        return sum;
    }


    public static class CreateNormalObject implements Callable<Boolean>{

        public Boolean call() throws Exception {
            long startTime = System.currentTimeMillis();
            for(int i=0;i<Loop;i++){
                new NormalObject("test");
            }
            newNormalObjectTimes.add(System.currentTimeMillis()-startTime);
            return true;
        }
    }

    public static class CreatGuardianBizException implements Callable<Boolean>{

        public Boolean call() throws Exception {
            long startTime = System.currentTimeMillis();
            for(int i=0;i<Loop;i++){
                new GuardianBizException("test");
            }
            newGuardianBizExceptionTimes.add(System.currentTimeMillis()-startTime);
            return true;
        }
    }

    public static class CreatGuardianBizExtException implements Callable<Boolean>{

        public Boolean call() throws Exception {
            long startTime = System.currentTimeMillis();
            for(int i=0;i<Loop;i++){
                new GuardianBizExtException("test");
            }
            newGuardianBizExtExceptionTimes.add(System.currentTimeMillis()-startTime);
            return true;
        }
    }
}
