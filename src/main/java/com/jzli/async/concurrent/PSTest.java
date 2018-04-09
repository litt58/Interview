package com.jzli.async.concurrent;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/9
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class PSTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedBlockingDeque<>();
        AtomicInteger count = new AtomicInteger(1);
        AtomicInteger consumerCount = new AtomicInteger(1);
        int maxSize = 100;
        int queueSize = 5;
        Thread producer1 = new Thread(new Producer(queue, queueSize, maxSize, count), "生产者1");
        Thread producer2 = new Thread(new Producer(queue, queueSize, maxSize, count), "生产者2");
        Thread consumer1 = new Thread(new Consumer(queue, consumerCount, maxSize), "消费者1");
        Thread consumer2 = new Thread(new Consumer(queue, consumerCount, maxSize), "消费者2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}

class Consumer implements Runnable {
    private Queue<Integer> queue;
    private AtomicInteger count;
    private int maxSize;

    public Consumer(Queue<Integer> queue, AtomicInteger count, int maxSize) {
        this.queue = queue;
        this.count = count;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (count.get() < maxSize) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    queue.notifyAll();
                } else {
                    Integer poll = queue.poll();
                    count.set(poll);
                    System.out.println(Thread.currentThread().getName() + "消费：" + poll);
                    try {
                        TimeUnit.MICROSECONDS.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束！");
    }
}

class Producer implements Runnable {
    private Queue<Integer> queue;
    private int queueSize;
    private int maxSize;
    private AtomicInteger count;

    public Producer(Queue<Integer> queue, int queueSize, int maxSize, AtomicInteger count) {
        this.queue = queue;
        this.queueSize = queueSize;
        this.maxSize = maxSize;
        this.count = count;
    }

    @Override
    public void run() {
        while (count.get() <= maxSize) {
            synchronized (queue) {
                if (queue.size() == queueSize) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        TimeUnit.MICROSECONDS.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i = count.getAndIncrement();
                    queue.add(i);
                    System.out.println(Thread.currentThread().getName() + "生产：" + i);
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束！");
    }
}