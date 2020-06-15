package com.jzli.base.finall;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/12 17:53
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：finally块中执行修改返回结果是没有用处的。
 *               这是因为Java程序会把try或者catch块中的返回值保留，也就是暂时的确认了返回值，然后再去执行finally代码块中的语句。等到finally代码块执行完毕后，如果finally块中没有返回值的话，就把之前保留的返回值返回出去。
 * ========================================================
 */
public class TryFinallyDemo5 {
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
        }
    }


}
