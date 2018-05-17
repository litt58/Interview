package com.jzli.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/5/17
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：List使用迭代器可以在遍历的过程中去删除list中的数据
 * ========================================================
 */
public class ListRemoveTest {
    public static void main(String[] args) {
        List<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(5);
        List<Integer> b = new LinkedList<>();
        b.add(5);
        b.add(6);
        b.add(3);
        for (Integer i : b) {
            Iterator<Integer> iterator = a.iterator();
            while (iterator.hasNext()) {
                if (i.equals(iterator.next())) {
                    iterator.remove();
                }
            }
        }

        for (Integer j : a) {
            System.out.println(j);
        }
    }
}
