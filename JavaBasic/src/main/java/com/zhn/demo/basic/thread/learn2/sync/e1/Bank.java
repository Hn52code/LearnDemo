package com.zhn.demo.basic.thread.learn2.sync.e1;

import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    public Bank(int nu, double initialBalance) {
        this.accounts = new double[nu];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {

        if (accounts[from] < amount) return;
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d" , amount , from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    }

    public double getTotalBalance() {
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
