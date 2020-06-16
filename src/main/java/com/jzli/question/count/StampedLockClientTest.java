package com.jzli.question.count;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

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
public class StampedLockClientTest {
    private int number = 0;
    private final StampedLock lock = new StampedLock();

    private void read() {
        // 尝试乐观读取
        long stamp = lock.tryOptimisticRead();
        int readNumber = number;
        System.out.println("乐观读取到的 number = " + readNumber);
        // 检查乐观读取到的数据是否有误
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            System.out.println("乐观读取到的 number " + readNumber + " 有误，换用悲观锁重新读取：number = " + number);
            lock.unlockRead(stamp);
        }
    }

    private void write(int change) {
        long stamp = lock.writeLock();
        number += change;
        System.out.println("写入 " + number);
        lock.unlock(stamp);
    }

    @Test
    public void test() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        // 开启一个线程写入 100 次 number
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                write(1);
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
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }
}