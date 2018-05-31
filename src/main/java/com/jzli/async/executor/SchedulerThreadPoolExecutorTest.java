package com.jzli.async.executor;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/5/31
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class SchedulerThreadPoolExecutorTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                long random = (long) (Math.random() * 10);
                System.out.println(new Date().toLocaleString() + "\t" + random);
                TimeUnit.SECONDS.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS);


    }
}
