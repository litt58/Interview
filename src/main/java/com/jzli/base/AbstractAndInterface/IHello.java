package com.jzli.base.abstractAndInterface;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/13
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：接口里定义的变量只能是公共的静态的常量
 * ========================================================
 */
public interface IHello {
    static String name = "李金钊";

    String sayHello(String name);
}
