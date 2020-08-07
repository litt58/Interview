package com.jzli.async.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/8/4 15:54
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：synchronized修饰非静态方法时，只对该方法加锁，不影响其他非synchronized修饰的方法
 * ========================================================
 */
public class SyncMethodTest {

    public void A() {
        System.out.println(Thread.currentThread().getName() + " A方法开始");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " A方法结束");
    }

    public synchronized void B() {
        System.out.println(Thread.currentThread().getName() + " B方法开始");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " B方法结束");
    }

    public static void main(String[] args) {
        SyncMethodTest test = new SyncMethodTest();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                test.B();
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                test.A();
            }).start();
        }


    }
}
