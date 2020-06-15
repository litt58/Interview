package com.jzli.singleton;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/15 16:15
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：饿汉模式实现单例类
 * ========================================================
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    //静态代码块使用饿汉模式
//    static {
//        instance = new HungrySingleton();
//    }

    private HungrySingleton() {
    }

    public static final HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            HungrySingleton instance = HungrySingleton.getInstance();
            System.out.println(instance);
        });
        Thread t2 = new Thread(() -> {
            HungrySingleton instance = HungrySingleton.getInstance();
            System.out.println(instance);
        });
        t1.start();
        t2.start();
    }
}
