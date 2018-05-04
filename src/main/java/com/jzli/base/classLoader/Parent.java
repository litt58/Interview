package com.jzli.base.classLoader;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/5/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class Parent {
    public static int number = 5;
    public static final String TYPE = "TYPE";


    private String name;

    public Parent() {
        System.out.println("Parent构造函数");
    }

    public Parent(String name) {
        this.name = name;
        System.out.println("Parent有参数的构造函数");
    }

    static {
        System.out.println("Parent Static代码块");
    }
}
