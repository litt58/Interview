package com.jzli.async.executor.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 打印序号，并休眠一秒钟
 *
 * @author lijinzhao
 */
public class SleepTask implements Runnable {
    private Logger _log = LoggerFactory.getLogger(this.getClass());
    private int i;

    public SleepTask(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
        try {
            _log.info(i + "");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            _log.error(e.getMessage(), e);
        }
    }

}