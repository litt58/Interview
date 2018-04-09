package com.jzli.async.concurrent.PS;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
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