package com.zhn.demo.basic.time;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Timer {

    private static ExecutorService service = Executors.newScheduledThreadPool(2);
    private volatile boolean active = true;
    private volatile boolean openSleep = false;
    private volatile long activeTimestamp;

    public Timer() {
        setActiveTimestamp(System.currentTimeMillis());
        // 创建时调用等待休眠
        waitSleep();
    }

    public void doBusiness1() {
        System.out.println("调用业务1中...");
        waitSleep();
    }

    public void doBusiness2() {
        System.out.println("调用业务2中...");
        sleep();
    }

    private void waitSleep() {
        if (isOpenSleep()) {
            setActiveTimestamp(System.currentTimeMillis());
            System.out.println("重新计时");
        } else {
            service.submit(this::doWaitSleep);
        }
    }

    private void sleep() {
        setActive(false);
    }

    // 休眠
    private void doWaitSleep() {
        if (!isActive()) return;
        System.out.println("开启休眠计时");
        setOpenSleep(true);
        while (isActive() && (System.currentTimeMillis() - getActiveTimestamp() < 5000)) {
            System.out.println("等待休眠");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        setActive(false);
        setOpenSleep(false);
        System.out.println("休眠中");
    }

    private synchronized boolean isOpenSleep() {
        return openSleep;
    }

    private synchronized void setOpenSleep(boolean openSleep) {
        this.openSleep = openSleep;
    }

    public synchronized boolean isActive() {
        return active;
    }

    private synchronized void setActive(boolean active) {
        this.active = active;
    }

    private synchronized long getActiveTimestamp() {
        return activeTimestamp;
    }

    private synchronized void setActiveTimestamp(long activeTimestamp) {
        this.activeTimestamp = activeTimestamp;
    }
}
