package com.jzli.base.abstractAndInterface;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/13
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：抽象类是单继承，接口是多实现；抽象类可以实现方法；
 *                抽象类除了不能实例化，其他的与普通java类一样；
 * ========================================================
 */
public abstract class AbstractHello {
    private String name;

    public static String value = "值";

    public AbstractHello() {
    }

    public AbstractHello(String name) {
        this.name = name;
    }

    public abstract String sayHello(String name);

    public static void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        AbstractHello.test();
        int oldCapacity=8;
        int i = oldCapacity >> 1;
        System.out.println(i);
    }
}
