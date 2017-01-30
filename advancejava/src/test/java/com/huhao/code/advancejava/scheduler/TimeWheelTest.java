package com.huhao.code.advancejava.scheduler;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by huhao on 2017/1/29.
 */
public class TimeWheelTest {
    TimeWheel timeWheel = new TimeWheel();

    @Before
    public void setUp(){
        timeWheel.build().start();

    }

    @Test
    public void testTimeWheel(){
        System.out.println("main Thread:"+Thread.currentThread().getName());
        for(int i=0;i<10;i++){
            MockTask mockTask = new MockTask(new Long(i),1000L);
            try {
                timeWheel.registerTask(mockTask);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        while(true){
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
