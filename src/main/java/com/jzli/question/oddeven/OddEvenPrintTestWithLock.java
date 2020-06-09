package com.jzli.question.oddeven;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/9 16:17
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：两个线程交替打印奇数偶数，使用Lock实现。https://github.com/crossoverJie/JCSprout/blob/master/src/main/java/com/crossoverjie/actual/TwoThread.java
 * ========================================================
 */
public class OddEvenPrintTestWithLock implements Runnable {
    /**
     * 重入锁
     */
    private final static Lock LOCK = new ReentrantLock();

    private static volatile Integer counter = 0;

    private boolean isOdd = false;

    public OddEvenPrintTestWithLock(boolean isOdd) {
        this.isOdd = isOdd;
    }

    public static void main(String[] args) {
        new Thread(new OddEvenPrintTestWithLock(false)).start();
        new Thread(new OddEvenPrintTestWithLock(true)).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                LOCK.lock();
                if (isOdd) {
                    if (counter % 2 == 0) {
                        int i = ++counter;
                        if (i > 100) {
                            return;
                        }
                        System.out.println("奇数线程：" + i);
                    }
                } else {
                    if (counter % 2 != 0) {
                        int i = ++counter;
                        if (i > 100) {
                            return;
                        }
                        System.out.println("偶数线程：" + i);
                    }
                }
            } finally {
                LOCK.unlock();
            }
        }
    }
}
