package com.jzli.consistenthash;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2019/12/18 16:50
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：模拟机器节点
 * ========================================================
 */
@Data
@AllArgsConstructor
public class Node<T> {
    private String ip;
    private String name;
}
