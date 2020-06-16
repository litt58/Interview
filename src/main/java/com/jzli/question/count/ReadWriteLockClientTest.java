package com.jzli.question.count;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/16 16:09
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：两个线程分别加100次和读取结果100次
 * ========================================================
 */
public class ReadWriteLockClientTest {
    private int number = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final Condition condition = writeLock.newCondition();
    /**
     * 标志是否写入完成
     */
    private boolean writeComplete = false;

    private void read() {
        readLock.lock();
        // 如果还没有写入完成，循环等待直到写入完成
        while (!writeComplete) {
            // 等待，并且不要阻塞写入
            try {
                writeLock.lock();
                condition.await();
                writeLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("number = " + number);
        readLock.unlock();
    }

    private void write(int change) {
        writeLock.lock();
        number += change;
        System.out.println("写入 " + number);
        writeLock.unlock();
    }

    @Test
    public void test() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        // 开启一个线程写入 100 次 number
        Thread t1 = new Thread(() -> {
            writeComplete = false;
            for (int i = 0; i < 100; i++) {
                write(1);
            }
            writeComplete = true;
            // 写入完成，唤醒读取线程，await/signal 操作必须在 lock 时执行。
            writeLock.lock();
            condition.signal();
            writeLock.unlock();
        });

        // 开启一个线程读取 100 次 number
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                read();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }
}