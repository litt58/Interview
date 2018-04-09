package com.jzli.async.concurrent.PS;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
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