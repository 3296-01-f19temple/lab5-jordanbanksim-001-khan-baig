# BankSim

## Task 1

When ran, BankSim simulates the transactions of 10 accounts whose total initial balances equals 10,000 each. So 10 accounts with 10,000 each would give a total bank balance of 100,000. Throughout the simulation, money can be withdrawn from one account and be deposited into another account. The simulation ends when the total balance does not equal 100,000, indicating that money has somehow been gained or lost.

The major race condition that inhibits the successful execution of the Bank can be found in the transfer function which calls the test function. While withdrawing an amount from one account and depositing the same amount to another account, BankSim encounters a problem when multiple threads try to modify data simultaneously. Since multiple accounts will be accessing Bank at the same time, this can create a problem where the accounts can disrupt the transfer process by accessing funds before or after their designated time, resulting in funds being lost or gained from the total balance of 100,000, thus leading to BankSim closing.

### Task 2

Synchronized locks does not offer any mechanism of waiting queue in which after the execution of one thread any thread running in parallel can acquire the lock. Due to which the thread which is there in the system and running for a longer period of time never gets chance to access the shared resource thus leading to starvation.

Reentrant locks are very much flexible and has a fairness policy in which if a thread is waiting for a longer time and after the completion of the currently executing thread we can make sure that the longer waiting thread gets the chance of accessing the shared resource hereby decreasing the throughput of the system and making it more time consuming.

