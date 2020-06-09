package com.jzli.gc;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/9 17:57
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：https://www.cnblogs.com/grey-wolf/p/11232968.html
 * ========================================================
 */
public class GcDemo {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //触发Minor GC
        allocation4 = new byte[4 * _1MB];
    }
}
