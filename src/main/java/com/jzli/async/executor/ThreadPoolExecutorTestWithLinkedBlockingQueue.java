package com.jzli.async.executor;

import com.jzli.async.executor.task.SleepTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试使用LinkedBlockingQueue无界队列，作为任务接收队列时 。 测试结果：不创建新的线程，最大线程数不起作用，始终只有核心线程数的线程。
 * 测试结论:不建议使用
 * <p>
 * <p>
 * 官方文档说明:无界队列。使用无界队列（例如，不具有预定义容量的 LinkedBlockingQueue）将导致在所有 corePoolSize
 * 线程都忙时新任务在队列中等待。这样，创建的线程就不会超过 corePoolSize。（因此，maximumPoolSize
 * 的值也就无效了。）当每个任务完全独立于其他任务，即任务执行互不影响时，适合于使用无界队列；例如，在 Web
 * 页服务器中。这种排队可用于处理瞬态突发请求，当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性。
 *
 * @author lijinzhao
 */
public class ThreadPoolExecutorTestWithLinkedBlockingQueue {
    private Logger _log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {
        new ThreadPoolExecutorTestWithLinkedBlockingQueue().test();
    }

    private int coreSize = 10;
    private int maxSize = 100;

    public void test() throws Exception {
        ThreadPoolExecutor threadPool = null;
        // 使用有界队列时，当任务数大于核心线程数加上队列大小时，线程池才会创建新的线程执行任务，但此时拒绝任务的处理程序也会拒绝掉一部分的任务
        threadPool = new ThreadPoolExecutor(coreSize, maxSize, 30L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        List<Future<Integer>> list = new LinkedList<Future<Integer>>();
        for (int i = 1; i <= 500; i++) {
            list.add(threadPool.submit(new SleepTask(i), i));
        }

        int count = 0;
        do {
            count = threadPool.getActiveCount();
            _log.info(threadPool.getPoolSize() + "");
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

        count = 0;
        do {
            count = threadPool.getActiveCount();
            TimeUnit.SECONDS.sleep(1);
        } while (count > 0);

        _log.info(threadPool.getPoolSize() + "");
        threadPool.shutdown();

        _log.info(threadPool.getPoolSize() + "");
        for (Future<Integer> future : list) {
            try {
                _log.info("result=" + future.get());
            } catch (Exception e) {
                _log.error(e.getMessage(), e);
            }
        }
    }

}