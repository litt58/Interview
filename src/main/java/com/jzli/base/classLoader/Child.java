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
public class Child extends Parent {
    private int id;

    public Child() {
        System.out.println("Child构造函数");
    }

    public Child(int id) {
        this.id = id;
        System.out.println("Child有参数的构造函数");
    }

    static {
        System.out.println("Child Static代码块");
    }

}
