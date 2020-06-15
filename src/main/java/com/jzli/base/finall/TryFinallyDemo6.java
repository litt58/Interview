package com.jzli.base.finall;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/12 17:53
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：finally块中执行修改返回结果是没有用处的。
 * ========================================================
 */
public class TryFinallyDemo6 {
    public static void main(String[] args) {
        System.out.println("执行结果为：" + getTotal());
    }

    public static int getTotal() {
        int i = 0;
        try {
            System.out.println("执行try");
            return i;
        } catch (Exception e) {
            System.out.println("执行catch");
            return 2;
        } finally {
            i++;
            System.out.println("执行finally");
            return i;
        }
    }


}
