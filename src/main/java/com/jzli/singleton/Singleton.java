package com.jzli.singleton;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/5/29
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class Singleton {
    private static volatile Singleton instance;
    public static Object lock = new Object();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            //静态变量等同于类
//            synchronized (lock) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(lock);
            System.out.println(instance);
        });
        Thread t2 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(lock);
            System.out.println(instance);
        });
        t1.start();
        t2.start();
    }
}
