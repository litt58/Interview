package com.jzli.async.concurrent.PS;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
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
