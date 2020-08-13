package com.jzli.async.future;

import java.util.Date;
import java.util.concurrent.*;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/7/3
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Callable<String> callable = () -> {
            String name = Thread.currentThread().getName();
            String s = new Date().toLocaleString();
            TimeUnit.SECONDS.sleep(5);
            return name + "\t" + s;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> submit1 = executorService.submit(callable);
        //这种方式没有返回值
        Future<?> submit2 = executorService.submit(new FutureTask<>(callable));
        executorService.shutdown();
        String s1 = submit1.get();
        System.out.println(s1);
        String s2 = (String) submit2.get(10, TimeUnit.SECONDS);
        System.out.println(s2);

    }
}
