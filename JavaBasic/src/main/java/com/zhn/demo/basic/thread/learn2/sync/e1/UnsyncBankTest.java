package com.zhn.demo.basic.thread.learn2.sync.e1;

public class UnsyncBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_BALANCE = 1000;
    private static final int DELAY = 100;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int form = i;
            new Thread(() -> {
                try {
                    while (true) {
                        int to = (int) (bank.size() * Math.random());
                        double account = MAX_BALANCE * Math.random();
                        bank.transfer(form, to, account);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {

                }
            }).start();
        }
    }

}
