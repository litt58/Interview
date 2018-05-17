package com.jzli.async.concurrent;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/5/17
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：Thread.yield()会暂停当前正在执行的线程对象，并执行其他线程。
 * ========================================================
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new YieldTask());
        Thread t2 = new Thread(new Task());
        t1.start();
        t2.start();
    }
}


class YieldTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程1第" + i + "次执行！");
            if (i > 3) {
                Thread.yield();
            }
        }
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程2第" + i + "次执行！");
        }
    }
}