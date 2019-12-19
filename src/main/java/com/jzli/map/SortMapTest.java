package com.jzli.map;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2019/12/19 19:39
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class SortMapTest {
    public static void main(String[] args) {
        SortedMap<Integer, Integer> sortMap = new TreeMap<>();
        for (int i = 1; i < 101; i++) {
            sortMap.put(i, i);
        }

        SortedMap<Integer, Integer> subMap = sortMap.tailMap(99);
        Set<Integer> keySet = subMap.keySet();
        for (Integer key : keySet) {
            System.out.println(key + "=" + subMap.get(key));
        }
    }
}
