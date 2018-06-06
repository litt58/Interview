package com.jzli.bloomfilter;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/6/6
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：布隆过滤guava实现
 * ========================================================
 */
public class GuavaBloomFilterDemo {

    private static int size = 1000000;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        long startTime = System.nanoTime(); // 获取开始时间
        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime();   // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");

        ArrayList<Integer> integers = Lists.newArrayList(1000);
        for (int i = 1; i < 1000; i++) {
            if (bloomFilter.mightContain(size + i)) {
                integers.add(size + i);
            }
        }
        System.out.println(integers.size());
        integers.forEach(System.out::println);
    }
}
