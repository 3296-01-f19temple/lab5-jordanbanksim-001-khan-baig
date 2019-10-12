package edu.temple.cis.c3238.banksim;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 */

import java.util.concurrent.Semaphore;


public class Bank {
    public static final int NTEST = 10;
    private final Account[] accounts;
    public final Semaphore semaphore;
    private long ntransacts = 0;
    private final int initialBalance;
    private final int numAccounts;
    private boolean open;

    public Bank(int numAccounts, int initialBalance) {
        this.numAccounts = numAccounts;
        this.initialBalance = initialBalance;
        semaphore = new Semaphore(this.numAccounts);
        accounts = new Account[numAccounts];
        ntransacts = 0;
        open = true;

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(this, i, initialBalance);
        }
    }

    public void transfer(int from, int to, int amount) {
        //accounts[from].waitForAvailableFunds(amount);
        if (!open) {
            return;
        }
        try {
            // Request a permit, if available...become blocked if not available.
            semaphore.acquire();
            if (accounts[from].withdraw(amount)) {
                accounts[to].deposit(amount);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            // Release the permit when transaction is complete.
            semaphore.release();
        }
        if (shouldTest()) {
            test();
        }
    }

    
    public void test() {
        int sum = 0;
        for (Account account : accounts) {
            System.out.printf("%s %s%n", 
                    Thread.currentThread().toString(), account.toString());
            sum += account.getBalance();
        }
        System.out.println(Thread.currentThread().toString() + 
                " Sum: " + sum);
        if (sum != numAccounts * initialBalance) {
            System.out.println(Thread.currentThread().toString() + 
                    " Money was gained or lost");
            System.exit(1);
        } else {
            System.out.println(Thread.currentThread().toString() + 
                    " The bank is in balance");
        }
    }


    public int size() {
        return accounts.length;
    }
    
    public synchronized boolean isOpen() {
        return open;
    }
    
    public void closeBank() {
        synchronized (this) {
            open = false;
        }
        for (Account account : accounts) {
            synchronized(account) {
                account.notifyAll();
            }
        }
    }
    
    public synchronized boolean shouldTest() {
        return ++ntransacts % NTEST == 0;
    }
}