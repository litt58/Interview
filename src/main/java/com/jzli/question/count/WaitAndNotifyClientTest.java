package com.jzli.question.count;

import org.junit.Test;

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
public class WaitAndNotifyClientTest {
    private int number = 0;
    private final Object lock = new Object();
    /**
     * 标志是否写入完成
     */
    private boolean writeComplete = false;

    private void read() {
        synchronized (lock) {
            // 如果还没有写入完成，循环等待直到写入完成
            while (!writeComplete) {
                // 等待，并且不要阻塞写入
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("number = " + number);
        }
    }

    private void write(int change) {
        synchronized (lock) {
            number += change;
            System.out.println("写入 " + number);
        }
    }

    @Test
    public void test() throws InterruptedException {
        // 开启一个线程写入 100 次 number
        Thread t1 = new Thread(() -> {
            writeComplete = false;
            for (int i = 0; i < 100; i++) {
                write(1);
            }
            writeComplete = true;
            // 写入完成，唤醒读取线程，wait/notify 操作必须在 synchronized 中执行。
            synchronized (lock) {
                lock.notify();
            }
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
    }
}