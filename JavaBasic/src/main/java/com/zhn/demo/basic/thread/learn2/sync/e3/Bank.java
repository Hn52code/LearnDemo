package com.zhn.demo.basic.thread.learn2.sync.e3;

import java.util.Arrays;

public class Bank {

    private final double[] accounts;

    public Bank(int nu, double initialBalance) {
        this.accounts = new double[nu];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) {

        try {
            System.out.println("from account " + from + " money: " + accounts[from]
                    + "   account:" + amount);
            while (accounts[from] < amount)
                wait();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized double getTotalBalance() {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }

}
