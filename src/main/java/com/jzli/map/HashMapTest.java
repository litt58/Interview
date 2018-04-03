package com.jzli.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/2
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<TestBean, String> map = new HashMap<>();
        for (int i = 1; i < 101; i++) {
            map.put(new TestBean(i + "", i + ""), i + "");
        }
        String s = map.get(new TestBean(1 + "", 1 + ""));
        Map<TestBean, String> synchronizedMap = Collections.synchronizedMap(map);
        s = synchronizedMap.get(new TestBean(1 + "", 1 + ""));

        HashSet<TestBean> set = new HashSet<>();
        for (int i = 1; i < 101; i++) {
            set.add(new TestBean(i + "", i + ""));
        }
        TreeMap<Object, Object> objectObjectTreeMap = new TreeMap<>();

        Hashtable<TestBean, String> table = new Hashtable<>();
        for (int i = 1; i < 101; i++) {
            table.put(new TestBean(i + "", i + ""), i + "");
        }

        ConcurrentHashMap<TestBean, String> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 1; i < 101; i++) {
            concurrentHashMap.put(new TestBean(i + "", i + ""), i + "");
        }

        LinkedHashMap<TestBean, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 1; i < 101; i++) {
            linkedHashMap.put(new TestBean(i + "", i + ""), i + "");
        }
    }
}
