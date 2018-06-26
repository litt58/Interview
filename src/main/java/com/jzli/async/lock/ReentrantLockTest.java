package com.jzli.async.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/17
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        //公平锁，按照线程获取锁的顺序，来分配锁
        ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i < 11; i++) {
            new Thread(new LockTask(lock), i + "").start();
//            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}

class LockTask implements Runnable {
    private ReentrantLock lock;

    public LockTask(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            deal(lock);
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁," + lock.getQueueLength() + "," + lock.getHoldCount());
            lock.unlock();
        }
    }

    /**
     * 可重入锁特性
     *
     * @param lock
     */
    private void deal(ReentrantLock lock) {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "开始工作");
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束工作");
            lock.unlock();
        }
    }
}