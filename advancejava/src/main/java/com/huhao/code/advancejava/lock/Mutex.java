package com.huhao.code.advancejava.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 模拟锁机制
 *
 * Created by huhao on 2017/3/17.
 */
public class Mutex implements Lock{

    private final SyncLock syncLock = new SyncLock();

    @Override
    public void lock() {
        syncLock.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        syncLock.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return syncLock.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return syncLock.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        syncLock.release(1);
    }

    @Override
    public Condition newCondition() {
        return syncLock.newCondition();
    }

    private static class SyncLock extends AbstractQueuedSynchronizer{
        @Override
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int acquires){
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases){
            if(getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        protected Condition newCondition(){
            return new ConditionObject();
        }
    }
}
