package com.jzli.async.synchronize;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2017/8/7
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：synchronized实例对象锁
 * ========================================================
 */
public class AccountingSyncWithInstance implements Runnable {
    private int count;

    /**
     * synchronized方法实现synchronized实例对象锁
     */
    public synchronized void increase() {
        count++;
    }
//    public void increase() {
//        synchronized (this) {
//            count++;
//        }
//    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increase();
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        AccountingSyncWithInstance accountingSyncWithInstance = new AccountingSyncWithInstance();
        Thread thread1 = new Thread(accountingSyncWithInstance);
        Thread thread2 = new Thread(accountingSyncWithInstance);
        thread1.start();
        thread2.start();
        //等待线程结束
        thread1.join();
        thread2.join();
        System.out.println(accountingSyncWithInstance.getCount());
    }
}
