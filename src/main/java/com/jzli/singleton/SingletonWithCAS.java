package com.jzli.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/16 15:35
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：通过CAS实现单例类
 * ========================================================
 */
public class SingletonWithCAS {
    private static final AtomicReference<SingletonWithCAS> reference = new AtomicReference<SingletonWithCAS>();

    private SingletonWithCAS() {
    }

    public static SingletonWithCAS getInstance() {
        for (; ; ) {
            SingletonWithCAS singleton = reference.get();
            if (null != singleton) {
                return singleton;
            }
            singleton = new SingletonWithCAS();
            if (reference.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            SingletonWithCAS instance = SingletonWithCAS.getInstance();
            System.out.println(instance);
        }).start();

        new Thread(() -> {
            SingletonWithCAS instance = SingletonWithCAS.getInstance();
            System.out.println(instance);
        }).start();
    }
}
