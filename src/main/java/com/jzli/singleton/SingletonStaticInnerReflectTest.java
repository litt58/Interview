package com.jzli.singleton;

import java.lang.reflect.Constructor;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/16 15:08
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：反射破坏单例
 * ========================================================
 */
public class SingletonStaticInnerReflectTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.jzli.singleton.LazyInnerClassSingleton");
        Constructor<?> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Object o1 = constructor.newInstance();
        Object o2 = constructor.newInstance();
        System.out.println(o1 == o2);
        System.out.println(o1);
        System.out.println(o2);

    }
}
