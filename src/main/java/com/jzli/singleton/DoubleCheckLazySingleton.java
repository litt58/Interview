package com.jzli.singleton;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/15
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：双重检验使用synchronized实现懒汉单例类
 * ========================================================
 */
public class DoubleCheckLazySingleton {
    private static volatile DoubleCheckLazySingleton instance;
    public static Object lock = new Object();

    private DoubleCheckLazySingleton() {
    }

    public static DoubleCheckLazySingleton getInstance() {
        if (instance == null) {
            //静态变量等同于类
//            synchronized (lock) {
            synchronized (DoubleCheckLazySingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLazySingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            DoubleCheckLazySingleton instance = DoubleCheckLazySingleton.getInstance();
            System.out.println(lock);
            System.out.println(instance);
        });
        Thread t2 = new Thread(() -> {
            DoubleCheckLazySingleton instance = DoubleCheckLazySingleton.getInstance();
            System.out.println(lock);
            System.out.println(instance);
        });
        t1.start();
        t2.start();
    }
}
