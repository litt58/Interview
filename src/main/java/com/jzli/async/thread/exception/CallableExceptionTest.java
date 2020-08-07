package com.jzli.async.thread.exception;

import java.util.concurrent.FutureTask;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/8/7 11:23
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：Callable的异常只有调用get方法才会抛出
 * ========================================================
 */
public class CallableExceptionTest {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            try {
                FutureTask<Integer> futureTask = new FutureTask<>(new CallableTestTask(i));
                Thread thread = new Thread(futureTask);
                thread.start();
                futureTask.get();
            } catch (Exception e) {
                System.out.println("catch exception...");
            }
        }
    }
}
