package com.jzli.async.executor;

import com.jzli.async.executor.task.SleepTask;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用PriorityBlockingQueue优先级队列实现任务重要性提前执行，PriorityBlockingQueue存储的对象必须实现Comparable，以支持排序功能
 *
 * @author lijinzhao
 */
public class ThreadPoolExecutorTestWithPriorityBlockingQueue {
    public static void main(String[] args) throws Exception {
        new ThreadPoolExecutorTestWithPriorityBlockingQueue().test();
    }

    private int coreSize = 10;
    private int maxSize = 30;
    private int queueSize = 100;

    public void test() throws Exception {
        ThreadPoolExecutor threadPool;
        // 使用有界队列时，当任务数大于核心线程数加上队列大小时，线程池才会创建新的线程执行任务，但此时拒绝任务的处理程序也会拒绝掉一部分的任务
        threadPool = new ThreadPoolExecutor(coreSize, maxSize, 30L,
                TimeUnit.SECONDS, new PriorityBlockingQueue<>(queueSize),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 100; i++) {
            threadPool.execute(new SleepTask(i));
        }
    }
}