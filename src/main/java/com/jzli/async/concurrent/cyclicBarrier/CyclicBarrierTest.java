package com.jzli.async.concurrent.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/9
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：CyclicBarrier可以重用的计数器，可以在所有线程完成后，由其中一个线程继续执行一个Runnable。
 * ========================================================
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "开始执行...");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println(Thread.currentThread().getName() + "全部执行完毕!"));
        for (int i = 1; i < 11; i++) {
            new Thread(new CyclicBarrierTask(cyclicBarrier), "线程" + i).start();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕!");
    }
}
