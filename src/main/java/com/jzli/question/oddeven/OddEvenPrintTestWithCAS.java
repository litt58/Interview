package com.jzli.question.oddeven;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/9 17:42
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：两个线程交替打印奇数偶数，使用CAS操作volatile变量控制实现。https://www.cnblogs.com/grey-wolf/p/11217164.html,
 * ========================================================
 */
public class OddEvenPrintTestWithCAS {
    private static volatile boolean loopForOdd = true;
    private static volatile boolean loopForEven = true;
    private static long loopForOddOffset;
    private static long loopForEvenOffset;
    private static Unsafe unsafe;
    private static volatile int counter = 1;

    static {
        Field theUnsafeInstance = null;
        try {
            theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        theUnsafeInstance.setAccessible(true);
        try {
            unsafe = (Unsafe) theUnsafeInstance.get(Unsafe.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            loopForOddOffset = unsafe.staticFieldOffset
                    (OddEvenPrintTestWithCAS.class.getDeclaredField("loopForOdd"));
        } catch (Exception ex) {
            throw new Error(ex);
        }

        try {
            loopForEvenOffset = unsafe.staticFieldOffset
                    (OddEvenPrintTestWithCAS.class.getDeclaredField("loopForEven"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //奇数线程
        new Thread(() -> {
            while (true) {
                while (true) {
                    boolean b = unsafe.getBoolean(OddEvenPrintTestWithCAS.class, loopForOddOffset);
                    if (b) {
                        // 循环
                    } else {
                        break;
                    }
                }

                int counter1 = counter;
                if (counter1 > 100) {
                    break;
                }
                System.out.println("奇数线程：" + counter1);

                counter++;

                // 修改volatile，通知偶数线程停止循环，同时，准备让自己陷入循环
                unsafe.putBoolean(OddEvenPrintTestWithCAS.class, loopForOddOffset, true);
                unsafe.putBoolean(OddEvenPrintTestWithCAS.class, loopForEvenOffset, false);

            }

        }).start();

        //偶数线程
        new Thread(() -> {
            while (true) {
                while (true) {
                    boolean b = unsafe.getBoolean(OddEvenPrintTestWithCAS.class, loopForEvenOffset);
                    if (b) {
                        // 循环
                    } else {
                        break;
                    }
                }

                int counter12 = counter;
                if (counter12 > 100) {
                    break;
                }
                System.out.println("偶数线程：" + counter12);

                counter++;

                // 修改volatile，通知奇数线程停止循环,同时，准备让自己陷入循环
                unsafe.putBoolean(OddEvenPrintTestWithCAS.class, loopForOddOffset, false);
                unsafe.putBoolean(OddEvenPrintTestWithCAS.class, loopForEvenOffset, true);
            }
        }).start();

        // 先启动奇数线程
        loopForOdd = false;
    }
}
