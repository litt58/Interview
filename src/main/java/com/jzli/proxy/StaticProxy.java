package com.jzli.proxy;

import com.jzli.proxy.bean.HelloImpl;
import com.jzli.proxy.bean.IHello;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：静态代理
 * ========================================================
 */
public class StaticProxy implements IHello {
    private IHello hello = new HelloImpl();

    @Override
    public String hello(String str) {
        System.out.println("静态代理:");
        return hello.hello(str);
    }

    public static void main(String[] args) {
        StaticProxy staticProxy = new StaticProxy();
        System.out.println(staticProxy.hello("李金钊"));
    }
}
