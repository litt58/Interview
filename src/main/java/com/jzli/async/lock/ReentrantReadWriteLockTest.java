package com.jzli.async.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/17
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：读写锁，实现读读共享，写写互斥，读写互斥
 * ========================================================
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws Exception {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Thread read1 = new Thread(new ReadWriteTask(lock, 0));
        Thread read2 = new Thread(new ReadWriteTask(lock, 0));
        Thread write = new Thread(new ReadWriteTask(lock, 1));

        read1.start();
        read2.start();
        write.start();
    }

}

class ReadWriteTask implements Runnable {
    private ReentrantReadWriteLock lock;
    private int flag;

    public ReadWriteTask(ReentrantReadWriteLock lock, int flag) {
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 0) {
            read();
        } else {
            write();
        }
    }

    public void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.readLock().unlock();
                System.out.println("释放读锁" + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.writeLock().unlock();
                System.out.println("释放写锁" + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
