package com.jzli.singleton;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/15 16:20
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：枚举类实现单例类
 * ========================================================
 */
public enum  EnumSingleton {
    INSTANCE;

    public static void main(String[] args) {
        new Thread(() -> {
            EnumSingleton instance = EnumSingleton.INSTANCE;
            System.out.println(instance);
        }).start();

        new Thread(() -> {
            EnumSingleton instance = EnumSingleton.INSTANCE;
            System.out.println(instance);
        }).start();
    }
}
