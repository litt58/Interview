package com.jzli.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/4
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class ListTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            arrayList.add(i + "");
        }
        arrayList.get(6);
        arrayList.remove(7);
        arrayList.remove(10 + "");

        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 1; i < 101; i++) {
            linkedList.add(i + "");
        }
        linkedList.get(6);
        linkedList.remove(7);
        linkedList.remove(10 + "");

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 1; i < 101; i++) {
            queue.add(i + "");
        }
        queue.remove();
        queue.remove(10+"");
        queue.peek();
        queue.poll();
    }

}
