package com.jzli.async.delay;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/11/6
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class DelayedTest {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        new Thread(() -> {
            while (true) {
                Delayed take = null;
                try {
                    take = queue.take();
                    if (take != null) {
                        System.out.println(new Date().toLocaleString() + "执行" + take.toString());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        for (int i = 0; i < 5; i++) {
            DelayedTask delayedTask = new DelayedTask(i + 1, System.currentTimeMillis()+(i + 1) * 1000);
            queue.offer(delayedTask);
            System.out.println(new Date().toLocaleString() + "加入" + delayedTask.toString());
        }
    }
}
