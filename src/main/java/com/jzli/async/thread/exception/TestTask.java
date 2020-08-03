package com.jzli.async.thread.exception;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/7/31 21:01
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class TestTask implements Runnable {
    private int i;

    public TestTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        if (i == 5) {
            throw new IllegalArgumentException();
        }
        System.out.println(i);
    }
}
