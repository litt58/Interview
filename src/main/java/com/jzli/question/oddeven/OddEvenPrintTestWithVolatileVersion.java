package com.jzli.question.oddeven;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/9 16:32
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：两个线程交替打印奇数偶数，使用volatile变量控制实现。https://www.cnblogs.com/grey-wolf/p/11217164.html,
 * ========================================================
 */
public class OddEvenPrintTestWithVolatileVersion {
    private static volatile boolean loopForOdd = true;
    private static volatile boolean loopForEven = true;
    private static volatile int counter = 1;

    public static void main(String[] args) {
        //奇数线程
        new Thread(() -> {
            while (true) {
                while (loopForOdd) {
                }
                int counter = OddEvenPrintTestWithVolatileVersion.counter;
                if (counter > 100) {
                    break;
                }
                System.out.println("奇数线程：" + counter);
                OddEvenPrintTestWithVolatileVersion.counter++;
                // 修改volatile，通知偶数线程停止循环，同时，准备让自己陷入循环
                loopForEven = false;
                loopForOdd = true;
            }
        }).start();

        //奇数线程
        new Thread(() -> {
            while (true) {
                while (loopForEven) {
                }
                int counter = OddEvenPrintTestWithVolatileVersion.counter;
                if (counter > 100) {
                    break;
                }
                System.out.println("偶数线程：" + counter);
                OddEvenPrintTestWithVolatileVersion.counter++;
                // 修改volatile，通知偶数线程停止循环，同时，准备让自己陷入循环
                loopForOdd = false;
                loopForEven = true;
            }
        }).start();

        // 先启动奇数线程
        loopForOdd = false;
    }
}
