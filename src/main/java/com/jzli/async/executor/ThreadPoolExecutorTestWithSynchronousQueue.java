package com.jzli.async.executor;


import com.jzli.async.executor.task.SleepTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试使用SynchronousQueue的ThreadPoolExecutor线程池(Executors.newCachedThreadPool())。
 * 测试结果:将最大线程数设置为Integer.MAX_VALUE，则每一个任务创建一个新的线程执行，并且测试发现线程不会被销毁。
 * 测试结论:不建议使用，会造成资源的浪费 。
 * <p>
 * 官方文档说明:直接提交。工作队列的默认选项是 SynchronousQueue，它将任务直接提交给线程而不保持它们。
 * 在此，如果不存在可用于立即运行任务的线程，则试图把任务加入队列将失败，因此会构造一个新的线程
 * 。此策略可以避免在处理可能具有内部依赖性的请求集时出现锁。直接提交通常要求无界 maximumPoolSizes
 * 以避免拒绝新提交的任务。当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性。
 *
 * @author lijinzhao
 */
public class ThreadPoolExecutorTestWithSynchronousQueue {
    private Logger _log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {
        new ThreadPoolExecutorTestWithSynchronousQueue().test();
    }

    public void test() throws Exception {
        ThreadPoolExecutor threadPool;

        // 使用SynchronousQueue队列，为不断创建新的线程来完成任务，并会在自动销毁线程(???实验中并未自动销毁)
        threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1800,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // threadPool = new ThreadPoolExecutor(coreSize, maxSize, 30L,
        // TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(300),
        // new ThreadPoolExecutor.DiscardOldestPolicy());

        // threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        List<Future<Integer>> list = new LinkedList<Future<Integer>>();
        for (int i = 1; i <= 500; i++) {
            list.add(threadPool.submit(new SleepTask(i), i));
        }

        int count;
        do {
            count = threadPool.getActiveCount();
            TimeUnit.SECONDS.sleep(1);
        } while (count > 0);

        for (Future<Integer> future : list) {
            try {
                _log.info("result=" + future.get());
            } catch (Exception e) {
                _log.error(e.getMessage(), e);
            }
        }
        _log.info(threadPool.getPoolSize() + "");
        TimeUnit.SECONDS.sleep(60);
        _log.info("=====================================");
        _log.info(threadPool.getPoolSize() + "");

        list = new LinkedList<Future<Integer>>();
        for (int i = 1; i <= 100; i++) {
            list.add(threadPool.submit(new SleepTask(i), i));
        }

        do {
            count = threadPool.getActiveCount();
            TimeUnit.SECONDS.sleep(1);
        } while (count > 0);

        _log.info(threadPool.getPoolSize() + "");
        threadPool.shutdown();

        for (Future<Integer> future : list) {
            try {
                _log.info("result=" + future.get());
            } catch (Exception e) {
                _log.error(e.getMessage(), e);
            }
        }
    }

}
