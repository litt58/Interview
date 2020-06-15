package com.jzli.base.finall;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/12 17:53
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：finally不一定执行，进入try finally之后，在try直接退出jvm，是不会执行。
 *               不管是给try块中造了个异常，还是在try块中进行return，我们发现finally块还是会执行的。因为异常处理设计初衷就是让finally块始终执
 * ========================================================
 */
public class TryFinallyDemo3 {
    public static void main(String[] args) {
        System.out.println("执行结果为：" + getTotal());
    }

    public static int getTotal() {
        try {
            System.out.println("执行try");
            System.exit(0);
        } finally {
            System.out.println("执行finally");
        }
        return 0;
    }


}
