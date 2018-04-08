package com.jzli.reflect;

import com.jzli.proxy.bean.HelloImpl;
import com.jzli.proxy.bean.IHello;
import org.junit.Test;

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

    @Test
    public void getClazz() throws ClassNotFoundException {
        //第一种方式获取Class对象    
        IHello hello = new HelloImpl();
        Class<? extends IHello> clazz = hello.getClass();
        System.out.println(clazz.getName());

        //第二种方式获取Class对象  
        Class<HelloImpl> helloClass = HelloImpl.class;
        System.out.println(helloClass == clazz);
        //第三种方式获取Class对象  
        Class<?> clazz2 = Class.forName("com.jzli.proxy.bean.HelloImpl");
        System.out.println(helloClass == clazz2);
    }
}
