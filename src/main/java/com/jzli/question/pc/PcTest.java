package com.jzli.question.pc;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2020/7/17 9:41
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：使用wait和notifyAll实现生产者消费者模型
 * ========================================================
 */
public class PcTest {
    public static void main(String[] args) {
        new PcTest().test();
    }

    private LinkedList<Long> list = new LinkedList<>();
    private LongAdder longAdder = new LongAdder();
    private volatile boolean isRun = true;
    private volatile boolean isRun2 = true;

    public void test() {
        new Thread(new Consumer(list), "1号消费者").start();
        new Thread(new Consumer(list), "2号消费者").start();
        new Thread(new Producer(list, longAdder), "1号生产者").start();
        new Thread(new Producer(list, longAdder), "2号生产者").start();
    }

    class Producer implements Runnable {

        LinkedList<Long> list;
        LongAdder longAdder;
        Random r = new Random();

        public Producer(LinkedList<Long> list, LongAdder longAdder) {
            this.list = list;
            this.longAdder = longAdder;
        }


        @Override
        public void run() {
            while (isRun) {
                synchronized (list) {
                    try {
                        if (list.size() > 0) {
                            list.notifyAll();
                            continue;
                        }
                        longAdder.increment();
                        long l = longAdder.longValue();
                        if (l <= 100) {
                            list.add(l);
                            System.out.println(Thread.currentThread().getName() + "生产第" + l + "号任务");
                            int i = r.nextInt(100);
                            TimeUnit.MILLISECONDS.sleep(i);
                        } else {
                            isRun = false;
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Consumer implements Runnable {
        LinkedList<Long> list;

        Random r = new Random();

        public Consumer(LinkedList<Long> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (isRun2) {
                synchronized (list) {
                    try {
                        if (list.isEmpty()) {
                            list.wait();
                        } else {
                            Long remove = list.remove(0);
                            System.out.println(Thread.currentThread().getName() + "消费第" + remove + "号任务");
                            int i = r.nextInt(100);
                            TimeUnit.MILLISECONDS.sleep(i);
                            if (remove == 100) {
                                isRun2 = false;
                                break;
                            }
                            list.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
