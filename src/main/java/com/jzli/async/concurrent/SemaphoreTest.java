package com.jzli.async.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/9
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：Semaphore和锁有点类似，它一般用于控制对某组资源的访问权限。http://www.importnew.com/21889.html
 * ========================================================
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        int N = 5;
        int number = 1;
        Semaphore semaphore = new Semaphore(N);
        for (int i = 1; i < 11; i++) {
            new Thread(new SemaphoreTask(semaphore, number), "线程" + i).start();
        }
    }
}

class SemaphoreTask implements Runnable {
    private Semaphore semaphore;
    private int number;

    public SemaphoreTask(Semaphore semaphore, int number) {
        this.semaphore = semaphore;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(number);
            System.out.println(Thread.currentThread().getName() + "开始执行...");
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            System.out.println(Thread.currentThread().getName() + "执行完毕!");
            semaphore.release(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
