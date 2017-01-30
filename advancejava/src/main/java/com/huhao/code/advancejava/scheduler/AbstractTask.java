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

/**
 * Created by huhao on 2017/1/19.
 */
public abstract class AbstractTask implements Task {

    protected Long    period;

    protected Long    nextInSeconds = 0L;

    protected Long    nextInMinutes = 0L;

    protected Long    nextInHour    = 0L;

    protected Long    nextInDay     = 0L;

    protected boolean isWorked      = false;

    @Override
    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public Long getNextInSeconds() {
        return nextInSeconds;
    }

    public void setNextInSeconds(Long nextInSeconds) {
        this.nextInSeconds = nextInSeconds;
    }

    public Long getNextInMinutes() {
        return nextInMinutes;
    }

    public void setNextInMinutes(Long nextInMinutes) {
        this.nextInMinutes = nextInMinutes;
    }

    public Long getNextInHour() {
        return nextInHour;
    }

    public void setNextInHour(Long nextInHour) {
        this.nextInHour = nextInHour;
    }

    public Long getNextInDay() {
        return nextInDay;
    }

    public void setNextInDay(Long nextInDay) {
        this.nextInDay = nextInDay;
    }

    public void buildTaskExecuteTime(Integer slot) {
    }

    public boolean isWorked() {
        return isWorked;
    }

    public void setWorked(boolean worked) {
        isWorked = worked;
    }
}
