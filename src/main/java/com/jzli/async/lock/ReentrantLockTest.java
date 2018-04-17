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
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 1; i < 11; i++) {
            new Thread(new LockTask(lock), i + "").start();
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
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        }
    }
}