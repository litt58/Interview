package com.jzli.question.count;

import org.junit.Test;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/16 16:09
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：两个线程分别加1000次和减1000次，等到最终结果为0
 * ========================================================
 */
public class CountTest {
    private int number = 0;

    private void read() {
        System.out.println("number = " + number);
    }

    private void write(int change) {
        number += change;
    }

    @Test
    public void test() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        // 开启一个线程加 10000 次
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                write(1);
            }
            System.out.println("增加 10000 次已完成");
        });

        // 开启一个线程减 10000 次
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                write(-1);
            }
            System.out.println("减少 10000 次已完成");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // 读取结果
        read();
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }
}
