package com.huhao.code.advancejava.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huhao on 2016/12/10.
 */
public class RateLimiteTest {

    private ExecutorService executorService;

    private List<RateLimiterJob> rateLimiterJobs = Lists.newArrayList();

    private RateLimiter rateLimiter;

    @Before
    public void setUp(){
        executorService = Executors.newFixedThreadPool(6);
        rateLimiter = RateLimiter.create(2);
    }

    @Test
    public void test_rateLimitSimple(){


        for(int i=0;i<6;i++){
            rateLimiterJobs.add(new RateLimiterJob("job"+String.valueOf(i)));
        }
        // calculate executing time
        long start = System.currentTimeMillis();
        for(RateLimiterJob rateLimiterJob:rateLimiterJobs){
            rateLimiter.acquire();
            executorService.execute(rateLimiterJob);
        }
        long end = System.currentTimeMillis();
        long cost = end-start;

        System.out.println(cost);
        System.out.println("cost time = "+(cost/1000)+"."+(cost%1000));




    }
}
