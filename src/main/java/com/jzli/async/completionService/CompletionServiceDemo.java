package com.jzli.async.completionService;

import java.util.Random;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.Callable;

import java.util.concurrent.CompletionService;

import java.util.concurrent.ExecutionException;

import java.util.concurrent.ExecutorCompletionService;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.Future;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * CompletionService整合了Executor和BlockingQueue的功能。你可以将Callable任务提交给它去执行，然后使用类似于队列中的take和poll方法，在结果完整可用时获得这个结果，像一个打包的Future。
 * ExecutorCompletionService是实现CompletionService接口的一个类，并将计算任务委托给一个Executor。
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws Exception {
        CompletionServiceDemo c = new CompletionServiceDemo();
        c.count1();
        c.count2();
    }

    //使用阻塞容器保存每次Executor处理的结果，在后面进行统一处理
    public void count1() throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<Future<Integer>>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = exec.submit(getTask());
            queue.add(future);
        }
        int sum = 0;
        int queueSize = queue.size();
        for (int i = 0; i < queueSize; i++) {
            sum += queue.take().get();
        }
        System.out.println("总数为：" + sum);
        exec.shutdown();
    }

    //使用CompletionService(完成服务)保持Executor处理的结果
    public void count2() throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec);
        for (int i = 0; i < 10; i++) {
            completionService.submit(getTask());
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            //检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
            Future<Integer> future = completionService.take();
            sum += future.get();
        }

        System.out.println("总数为：" + sum);
        exec.shutdown();
    }

    //得到一个任务
    public Callable<Integer> getTask() {
        final Random rand = new Random();
        Callable<Integer> task = () -> {
            int i = rand.nextInt(10);
            int j = rand.nextInt(10);
            int sum = i * j;
            System.out.print(sum + "\t");
            return sum;
        };
        return task;
    }
}
