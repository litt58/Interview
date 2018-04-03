package com.jzli.async.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2017/10/16
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：对比Thread的start和run方法，start方法另起一个线程执行，run方法在本线程中执行
 * ========================================================
 */
public class TestWithThreadStartAndRun implements Runnable {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    int b = 100;

    public void m1() throws InterruptedException {
        logger.info("enter m1");
        b = 1000;
        Thread.sleep(1000);
        logger.info("m1:" + b);
    }

    public void m2() throws InterruptedException {
        logger.info("enter m2");
        Thread.sleep(500);
        b = 500;
        logger.info("m2:" + b);
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestWithThreadStartAndRun testWithThreadStartAndRun = new TestWithThreadStartAndRun();
        Thread thread = new Thread(testWithThreadStartAndRun);
//        thread.run();
        thread.start();
        testWithThreadStartAndRun.m2();
        testWithThreadStartAndRun.logger.info("main:" + testWithThreadStartAndRun.b);
    }
}
