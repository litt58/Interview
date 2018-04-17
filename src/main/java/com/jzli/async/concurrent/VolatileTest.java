package com.jzli.async.concurrent;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/17
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：volatile增加了实例变量在对线程之间的可见性，保证我们获得的是变量的最新值。
                  volatile在读上面保持了同步作用，但是在写上面不保持同步
 * ========================================================
 */
public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1000);
        runThread.setRunning(false);
    }
}

class RunThread extends Thread {
    private volatile boolean isRunning = true;


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始运行！");
        while (isRunning) {

        }
        System.out.println(Thread.currentThread().getName() + "被中止了！");
    }
}
