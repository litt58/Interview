package com.jzli.list;

import java.util.LinkedList;
import java.util.List;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/6/12 17:39
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：使用for循环正序遍历去删除，注意修改下标。
 * ========================================================
 */
public class ListRemoveByAes {
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
        for (int i = 0; i < a.size(); i++) {
            for (Integer n : b) {
                if (a.get(i).equals(n)) {
                    a.remove(i);
                    i--;
                }
            }
        }

        for (Integer j : a) {
            System.out.println(j);
        }
    }
}
