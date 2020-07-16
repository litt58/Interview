package com.jzli.async.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.StampedLock;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/7/16 18:31
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class StampedLockTest {
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        LinkedList<String> list = new LinkedList<>();
        long writeStampedLock = stampedLock.tryWriteLock();
        list.add("111");
        stampedLock.unlock(writeStampedLock);


        long stamped = stampedLock.tryOptimisticRead();
        try {
            if (stampedLock.validate(stamped)) {
                stamped = stampedLock.tryReadLock();
                list.forEach(System.out::println);
            }
        } finally {
            stampedLock.unlockRead(stamped);
        }
    }
}
