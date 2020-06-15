package com.jzli.base.finall;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/12 17:53
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：finally块执行在try或者catch的return之前
 * ========================================================
 */
public class TryFinallyDemo4 {
    public static void main(String[] args) {
        System.out.println("执行结果为：" + getTotal());
    }

    public static int getTotal() {
        try {
            System.out.println("执行try");
            return 1/0;
        } catch (Exception e) {
            System.out.println("执行catch");
            return 2;
        } finally {
            System.out.println("执行finally");
        }
    }


}
