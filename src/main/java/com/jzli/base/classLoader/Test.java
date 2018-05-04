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
public class Test {
    public static void main(String[] args) throws Exception {
        //加载类的方式1：只执行父类和子类的静态代码块
//        Class.forName("com.jzli.base.classLoader.Child");
        //加载类的方式2：什么都没有执行
//        Class<Child> childClass = Child.class;
        //非主动使用类字段：只执行父类的静态代码块
//        System.out.println(Child.number);
        //直接new父类:执行父类的静态代码块和父类的构造函数
//        Parent parent = new Parent();
        //直接new，会加载先执行父类的静态代码块，子类的静态代码块，父类的默认构造函数，子类的构造函数
//        Child child = new Child();
        //加载时静态代码块只执行一次，子类的构造方法会先调用父类默认的构造方法
        Parent parent = new Parent();
        Child child = new Child(6);
        //被动使用:数组不会引发初始化导致什么都没有执行
//        Child[] children = new Child[10];
        //被动使用:常量传播优化导致什么都没有执行
//        String type = Parent.TYPE;
    }
}
