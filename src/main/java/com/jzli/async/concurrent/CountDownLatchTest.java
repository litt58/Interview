package com.jzli.async.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/9
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：CountDownLatch最主要的功能就是以计数器的形式统一等待线程完成任务，将计数器归零后，再继续
 * ========================================================
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "开始执行...");
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i < 11; i++) {
            new Thread(new CountDownLatchTask(countDownLatch), "线程" + i).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "执行完毕!");
    }
}

class CountDownLatchTask implements Runnable {

    private CountDownLatch countDownLatch;

    public CountDownLatchTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始执行...");
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            System.out.println(Thread.currentThread().getName() + "执行完毕!");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}