package com.jzli.list;

import java.util.concurrent.*;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/6/21
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：DelayQueue延时队列的使用实践
 * ========================================================
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        DelayTask delayTask = new DelayTask(System.currentTimeMillis() + 1000 * 60);
        delayQueue.add(delayTask);

        while (true) {
            DelayTask take = delayQueue.take();
            Thread thread = new Thread(take);
            thread.start();
        }
    }
}

class DelayTask implements Delayed, Runnable {
    private long time;

    public DelayTask(long time) {
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long l = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (l > 0) {
            return 1;
        } else if (l < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis());
    }
}
