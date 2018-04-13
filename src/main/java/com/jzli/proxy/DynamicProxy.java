package com.jzli.proxy;

import com.jzli.proxy.bean.HelloImpl;
import com.jzli.proxy.bean.IHello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：JDK原生动态代理是Java原生支持的，不需要任何外部依赖，但是它只能基于接口进行代理。
 * ========================================================
 */
public class DynamicProxy implements InvocationHandler {
    private IHello hello;

    public DynamicProxy(IHello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("hello".equalsIgnoreCase(method.getName())) {
            System.out.println("动态代理:");
        } else {
            System.out.println(method.getName());
        }
        return method.invoke(hello, args);
    }

    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        IHello hello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),
                new Class<?>[]{IHello.class},
                dynamicProxy);
        System.out.println(hello.hello("李金钊"));
        hello.test();
    }
}
