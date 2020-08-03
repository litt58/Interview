package com.jzli.async.thread.exception;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/7/31 21:04
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public ThreadExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(name + "异常捕获到了：" + e);
    }
}
