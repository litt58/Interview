package com.jzli.async.executor;

import com.jzli.async.executor.task.SleepTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 测试使用ArrayBlockingQueue或者LinkedBlockingQueue有界队列，作为任务接收队列时 测试结果：
 * 1.当任务数大于核心线程数加上队列大小时
 * ，线程池才会创建新的线程执行任务，但此时如果最大线程数不足时，线程池会因为最大线程个数和队列的大小有限而使用拒绝任务处理程序拒绝掉一部分的任务。
 * 2.当队列数足够大时，则不会创建新的线程，而只使用核心线程来执行任务，这种情况下会导致任务执行缓慢 。
 * 3.当任务数大于队列大小与核心线程数之和时，线程池才会创建新的线程执行任务，如果最大线程数足够时，可以保住所有任务的快速正确完成。
 * 测试结论:建议使用，建议设置最大线程数足够大
 * (3000)，队列大小为一定数量(100)，核心线程数为一定数量(100)，保存时间为1800秒，同时发现创建线程时的命名规则好像是一直往上加的。
 * <p>
 * 官方文档说明：有界队列。当使用有限的 maximumPoolSizes 时，有界队列（如
 * ArrayBlockingQueue）有助于防止资源耗尽，但是可能较难调整和控制
 * 。队列大小和最大池大小可能需要相互折衷：使用大型队列和小型池可以最大限度地降低 CPU
 * 使用率、操作系统资源和上下文切换开销，但是可能导致人工降低吞吐量。如果任务频繁阻塞（例如，如果它们是 I/O
 * 边界），则系统可能为超过您许可的更多线程安排时间。使用小型队列通常要求较大的池大小，CPU
 * 使用率较高，但是可能遇到不可接受的调度开销，这样也会降低吞吐量。
 *
 * @author lijinzhao
 */
public class ThreadPoolExecutorTestWithArrayBlockingQueue {
    private Logger _log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {
        new ThreadPoolExecutorTestWithArrayBlockingQueue().test();
    }

    private int coreSize = 100;
    private int maxSize = 3000;
    private int queueSize = 100;

    public void test() throws Exception {
        ThreadPoolExecutor threadPool = null;
        // 使用有界队列时，当任务数大于核心线程数加上队列大小时，线程池才会创建新的线程执行任务，但此时拒绝任务的处理程序也会拒绝掉一部分的任务
        threadPool = new ThreadPoolExecutor(coreSize, maxSize, 30L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future<Integer>> list = new LinkedList<Future<Integer>>();
        for (int i = 1; i <= 500; i++) {
            list.add(threadPool.submit(new SleepTask(i), i));
        }

        int count = 0;
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
        // 获取池中的当前线程数
        _log.info(threadPool.getPoolSize() + "");
        TimeUnit.SECONDS.sleep(60);
        _log.info("=====================================");
        // 获取池中的当前线程数
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

        // 获取池中的当前线程数
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