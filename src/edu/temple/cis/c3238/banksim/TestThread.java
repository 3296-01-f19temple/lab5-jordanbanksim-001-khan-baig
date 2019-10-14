package edu.temple.cis.c3238.banksim;

public class TestThread extends Thread {

    private final Bank bank;
    private final Account[] accounts;
    private final int initialBalance;
    private final int numAccounts;

    public TestThread(Bank bank, Account[] accounts, int numAccounts, int initialBalance){
        this.bank = bank;
        this.accounts = accounts;
        this.numAccounts = numAccounts;
        this.initialBalance = initialBalance;
    }

    @Override
    public synchronized void run() {
        int sum = 0;
        try {
            bank.semaphore.acquire(numAccounts);
            for (Account account : accounts) {
                System.out.printf("%s %s%n", Thread.currentThread().toString(), account.toString());
                sum += account.getBalance();
            }
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        } 
        finally{
            bank.semaphore.release(numAccounts);
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
}