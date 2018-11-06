package com.jzli.async.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/11/6
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class DelayedTask implements Delayed{
    private int id;
    private long executeTime;

    public DelayedTask(int id, long executeTime) {
        this.id = id;
        this.executeTime = executeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask d = (DelayedTask) o;
        return executeTime > d.executeTime ? 1 : (executeTime < d.executeTime ? -1 : 0);
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "id=" + id +
                ", executeTime=" + executeTime +
                '}';
    }
}
