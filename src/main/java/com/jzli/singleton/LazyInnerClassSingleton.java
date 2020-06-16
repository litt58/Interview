package com.jzli.singleton;

import java.io.Serializable;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/15
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：静态内部类实现单例类，懒汉模式最优解
 * ========================================================
 */
public class LazyInnerClassSingleton implements Serializable {
    /**
     * 静态内部类
     */
    private static class LazyInnerClassSingletonHolder {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }

    private LazyInnerClassSingleton() {
        // 防止反射创建多个对象
        if (LazyInnerClassSingletonHolder.LAZY != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * 防止序列化创建多个对象,这个方法是关键
     */
    private Object readResolve() {
        return LazyInnerClassSingletonHolder.LAZY;
    }

    public static final LazyInnerClassSingleton getInstance() {
        return LazyInnerClassSingletonHolder.LAZY;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LazyInnerClassSingleton instance = LazyInnerClassSingleton.getInstance();
            System.out.println(instance);
        });
        Thread t2 = new Thread(() -> {
            LazyInnerClassSingleton instance = LazyInnerClassSingleton.getInstance();
            System.out.println(instance);
        });
        t1.start();
        t2.start();
    }
}
