package com.jzli.reflect;

import com.jzli.proxy.bean.HelloImpl;
import com.jzli.proxy.bean.IHello;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/8
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class ReflectTest {

    /**
     * 在运行期间，一个类，只有一个Class对象产生。
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void getClazz() throws ClassNotFoundException {
        //第一种方式获取Class对象    
        IHello hello = new HelloImpl();
        Class<? extends IHello> clazz = hello.getClass();
        System.out.println(clazz.getName());
        //第二种方式获取Class对象  
        Class<HelloImpl> helloClass = HelloImpl.class;
        System.out.println(helloClass == clazz);
        //第三种方式获取Class对象,常用  
        Class<?> clazz2 = Class.forName("com.jzli.proxy.bean.HelloImpl");
        System.out.println(helloClass == clazz2);
    }

    @Test
    public void getConstructor() throws Exception {
        Class<?> clazz = Class.forName("com.jzli.proxy.bean.HelloImpl");
        //公共的构造方法
        Constructor<?>[] constructors = clazz.getConstructors();
        //所有的构造方法
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        Constructor<?> constructor = clazz.getConstructor(null);
        System.out.println(constructor);
        Object o = constructor.newInstance();
        System.out.println(o);
    }

    @Test
    public void getMethod() throws Exception {
        Class<?> clazz = Class.forName("com.jzli.proxy.bean.HelloImpl");
        Constructor<?> constructor = clazz.getConstructor(null);
        Object o = constructor.newInstance();
        //获取所有公共方法
        Method[] methods = clazz.getMethods();
        //获取所有方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method method = clazz.getMethod("hello", String.class);
        Object invoke = method.invoke(o, "李金钊");
        System.out.println(invoke);
    }

    @Test
    public void getField() throws Exception {
        Class<?> clazz = Class.forName("com.jzli.proxy.bean.HelloImpl");
        Constructor<?> constructor = clazz.getConstructor(null);
        Object o = constructor.newInstance();
        //获取所有公共的属性字段
        Field[] fields = clazz.getFields();
        //获取所有的属性字段
        Field[] declaredFields = clazz.getDeclaredFields();
        Field field = clazz.getDeclaredField("name");
        //非公共属性访问可见
        field.setAccessible(true);
        field.set(o, "李金钊");
        System.out.println(field.get(o));

        Field declaredField = clazz.getDeclaredField("ID");
        declaredField.setAccessible(true);
        //静态变量可以不用使用类实例
        System.out.println(declaredField.get(null));
    }

}
