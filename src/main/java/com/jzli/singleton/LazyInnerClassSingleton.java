package com.jzli.singleton;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/15
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：静态内部类实现单例类
 * ========================================================
 */
public class LazyInnerClassSingleton {
    /**
     * 静态内部类
     */
    private static class LazyInnerClassSingletonHolder {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }

    private LazyInnerClassSingleton() {
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
