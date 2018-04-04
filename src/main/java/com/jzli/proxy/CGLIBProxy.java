package com.jzli.proxy;

import com.jzli.proxy.bean.Hello;
import com.jzli.proxy.bean.HelloImpl;
import com.jzli.proxy.bean.IHello;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：CGLIB动态代理:CGLIB通过继承的方式进行代理，无论目标对象有没有实现接口都可以代理，但是无法处理final方法的情况，因为CGLib原理是动态生成被代理类的子类。
 * ========================================================
 */
public class CGLIBProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if ("hello".equalsIgnoreCase(method.getName())) {
            System.out.println("CGLib动态代理:");
        }
        return methodProxy.invokeSuper(o, objects);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloImpl.class);
        enhancer.setCallback(new CGLIBProxy());

        IHello hello = (IHello) enhancer.create();
        System.out.println(hello.hello("李金钊"));

        enhancer.setSuperclass(Hello.class);

        Hello h = (Hello) enhancer.create();
        System.out.println(h.hello("李金钊"));
    }
}
