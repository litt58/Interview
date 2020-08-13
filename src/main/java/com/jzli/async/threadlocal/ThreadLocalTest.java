package com.jzli.async.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/8/7 11:53
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set("李金钊");
                System.out.println(threadLocal.get());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.gc();
    }
}
