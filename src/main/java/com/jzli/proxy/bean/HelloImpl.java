package com.jzli.proxy.bean;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class HelloImpl implements IHello {
    private final static String ID = "ID";
    String name;

    @Override
    public String hello(String str) {
        return "Hello " + str;
    }
}
