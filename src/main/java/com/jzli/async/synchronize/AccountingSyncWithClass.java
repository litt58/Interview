package com.jzli.async.synchronize;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2017/8/7
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：synchronized对象类锁
 * ========================================================
 */
public class AccountingSyncWithClass implements Runnable {
    public static int count;

//    /**
//     * 静态方法实现synchronized对象类锁
//     */
//    public static synchronized void increase() {
//        count++;
//    }

    /**
     * 同步代码块方式实现synchronized对象类锁
     */
    public void increase() {
        synchronized (AccountingSyncWithClass.class) {
            count++;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AccountingSyncWithClass());
        Thread thread2 = new Thread(new AccountingSyncWithClass());
        Thread thread3 = new Thread(new AccountingSyncWithClass());
        thread1.start();
        thread2.start();
        thread3.start();
        //等待线程结束
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(AccountingSyncWithClass.count);
    }
}
