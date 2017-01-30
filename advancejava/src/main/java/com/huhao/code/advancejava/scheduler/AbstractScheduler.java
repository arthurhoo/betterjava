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
 * 抽象的调度器
 *
 * Created by huhao on 2017/1/18.
 */
public interface AbstractScheduler {

    boolean start();

    boolean stop();

    AbstractScheduler build();

    boolean unregistTask(AbstractTask task);

    void destroy();
}
