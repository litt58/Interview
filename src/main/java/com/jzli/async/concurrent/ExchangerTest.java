package com.jzli.async.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/9
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：Exchanger交换两个线程的值
 * ========================================================
 */
public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A";// A录入银行流水数据
                exchanger.exchange(A);
            } catch (InterruptedException e) {
            }
        });
        threadPool.execute(() -> {
            try {
                String B = "银行流水B";// B录入银行流水数据
                String A = exchanger.exchange("B");
                System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是："
                        + A + ",B录入是：" + B);
            } catch (InterruptedException e) {
            }
        });
        threadPool.shutdown();
    }
}