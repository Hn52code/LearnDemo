package com.zhn.demo.basic.thread.learn2.sync.e2;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private Condition condition;
    private final double[] accounts;
    private Lock bankLock;

    public Bank(int nu, double initialBalance) {
        this.accounts = new double[nu];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        condition = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) {
        bankLock.lock();
        try {
            System.out.println("from account " + from + " money: " + accounts[from] + "   account:" + amount);
            while (accounts[from] < amount)
                condition.await();
//            if (accounts[from] < amount) return;
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double account : accounts) {
                sum += account;
            }

            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }

}
