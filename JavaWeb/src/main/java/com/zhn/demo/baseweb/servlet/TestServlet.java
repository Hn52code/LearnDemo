package com.zhn.demo.baseweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@WebServlet("/test")
public class TestServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        for (int i = 0; i < 2; i++) {
            new Thread(()->b.task()).start();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private B b= new B();
    class B{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private volatile boolean occupy = false;

        private synchronized boolean isOccupy() {
            return occupy;
        }

        private synchronized void setOccupy(boolean occupy) {
            this.occupy = occupy;
        }

        private void task() {
            System.out.println(Thread.currentThread().getName() + ": 进场");
//        if (isOccupy()) {
//            System.out.println("锁被占用");
//            return;
//        }
            System.out.println(Thread.currentThread().getName() + ": " + isOccupy() + " " + this);
            if (!isOccupy() && lock.tryLock()) {
                try {
//                setOccupy(true);
                    occupy =true;
                    System.out.println(Thread.currentThread().getName() + ": " + isOccupy() + " " + this);
                    System.out.println(Thread.currentThread().getName() + ": 锁被获取");
                    condition.await(1, TimeUnit.SECONDS);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + ": 锁被释放");
//                setOccupy(false);
                    occupy = false;
                    lock.unlock();
                }
            } else System.out.println(Thread.currentThread().getName() + ": 锁被占用");
        }

    }



}
