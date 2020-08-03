package com.jzli.async.thread.exception;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/7/31 21:00
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：线程中的异常没有被捕获，只是在控制台打印了异常
 * 可以设置线程的setDefaultUncaughtExceptionHandler
 * ========================================================
 */
public class ThreadExceptionTest {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("默认"));
        for (int i = 1; i <= 10; i++) {
            try {
                Thread t = new Thread(new TestTask(i));
                t.setUncaughtExceptionHandler(new ThreadExceptionHandler("指定"));
                t.start();
            } catch (Exception e) {
                System.out.println("catch exception...");
            }
        }
    }
}
