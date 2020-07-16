package com.jzli.list;

import java.util.concurrent.LinkedTransferQueue;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/7/16 15:55
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class LinkedTransferQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue<String>();
        linkedTransferQueue.transfer("String");
    }
}
