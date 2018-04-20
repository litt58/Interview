package com.jzli.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/20
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：Hash碰撞和扩容导致HashMap线程不安全
 * ========================================================
 */
public class HashMapUnSafeTest {
    public static void main(String[] args) throws InterruptedException {
        int n = 8;
        HashMap<TestBean, String> map = new HashMap<>();
        HashMap<TestBean, String> unSafeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            new Thread(new HashMapUnSafeTask(unSafeMap, i)).start();
        }

        for (int i = 0; i < n; i++) {
            map.put(new TestBean(i + "", i + ""), i + "");
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(map.size() + "\t" + unSafeMap.size());
        Set<Map.Entry<TestBean, String>> entries = map.entrySet();
        for (Map.Entry<TestBean, String> entity : entries) {
            TestBean key = entity.getKey();
            String value = entity.getValue();
            System.out.println(key + " = " + value + "\t" + "Safe");
        }

        entries = unSafeMap.entrySet();
        for (Map.Entry<TestBean, String> entity : entries) {
            TestBean key = entity.getKey();
            String value = entity.getValue();
            System.out.println(key + " = " + value + "\t" + "UnSafe");
        }
    }


}

class HashMapUnSafeTask implements Runnable {
    private HashMap<TestBean, String> map;
    private int i;

    public HashMapUnSafeTask(HashMap<TestBean, String> map, int i) {
        this.map = map;
        this.i = i;
    }

    @Override
    public void run() {
        map.put(new TestBean(i + "", i + ""), i + "");
    }
}