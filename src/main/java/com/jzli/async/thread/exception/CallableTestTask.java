package com.jzli.async.thread.exception;

import java.util.concurrent.Callable;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/8/7 11:22
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class CallableTestTask implements Callable<Integer> {
    private int i;

    public CallableTestTask(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        if (i == 5) {
            throw new IllegalArgumentException();
        }
        System.out.println(i);
        return i;
    }
}
