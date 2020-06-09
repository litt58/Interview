package com.jzli.question.oddeven;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/9 15:32
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：两个线程交替打印奇数偶数，使用wait和notify实现。https://www.cnblogs.com/grey-wolf/p/11217164.html,
 * ========================================================
 */
public class OddEvenPrintTestWithWaitAndNotify {
    private static volatile Integer counter = 0;
    private static Object monitor = new Object();

    public static void main(String[] args) {
        //奇数线程
        new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    if (counter % 2 != 0) {
                        continue;
                    }
                    int i = ++counter;
                    if (i > 100) {
                        return;
                    }
                    System.out.println("奇数线程：" + i);
                    try {
                        monitor.notify();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        //偶数线程
        new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    if (counter % 2 == 0) {
                        continue;
                    }
                    int i = ++counter;
                    if (i > 100) {
                        return;
                    }
                    System.out.println("偶数线程：" + i);
                    try {
                        monitor.notify();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
