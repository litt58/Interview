package com.jzli.base.finall;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/12 17:53
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：finally不一定执行，未进入try finally之前有异常是不会执行。
 * ========================================================
 */
public class TryFinallyDemo2 {
    public static void main(String[] args) {
        System.out.println("执行结果为：" + getTotal());
    }

    public static int getTotal() {
        int i = 11 / 0;
        if (i == 11) {
            return i;
        }
        try {
            System.out.println("执行try");
        } finally {
            System.out.println("执行finally");
        }
        return 0;
    }


}
