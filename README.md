# BankSim

## Task 1

When ran, BankSim simulates the transactions of 10 accounts whose total initial balances equals 10,000 each. So 10 accounts with 10,000 each would give a total bank balance of 100,000. Throughout the simulation, money can be withdrawn from one account and be deposited into another account. The simulation ends when the total balance does not equal 100,000, indicating that money has somehow been gained or lost.

The major race condition that inhibits the successful execution of the Bank can be found in the transfer function which calls the test function. While withdrawing an amount from one account and depositing the same amount to another account, BankSim encounters a problem when multiple threads try to modify data simultaneously. Since multiple accounts will be accessing Bank at the same time, this can create a problem where the accounts can disrupt the transfer process by accessing funds before or after their designated time, resulting in funds being lost or gained from the total balance of 100,000, thus leading to BankSim closing.

![Use Case Image](BankUMLSequenceDiagram.JPG)

## Task 2

Synchronized locks does not offer any mechanism of waiting queue in which after the execution of one thread any thread running in parallel can acquire the lock. Due to which the thread which is there in the system and running for a longer period of time never gets chance to access the shared resource thus leading to starvation.

Reentrant locks are very much flexible and has a fairness policy in which if a thread is waiting for a longer time and after the completion of the currently executing thread we can make sure that the longer waiting thread gets the chance of accessing the shared resource hereby decreasing the throughput of the system and making it more time consuming.

## Task 3

The second race condition is that a thread was used to sum up all the funds while other threads were simultaenously still transferring funds. This race condition was solved by refactoring the Bank class so that the testing method occurs on a separate thread from the transfer thread. Mutual exclusion was implemented between the testing thread and the transferring thread. The testing thread acquires the semaphore, sums up the total balances of all of the accounts, then releases the semaphore and signals to the other (transfer) threads.

## Task 4

 

## Task 5

This deadlock condition is caused by the fact that there is nothing telling BankSim to terminate so it will stay running even though it has reached the predefined number of transfers. To get rid of this deadlock condition, an implementation must be created which notifies BankSim that it has executed the predefined number of transfers and that it is safe to terminate.  

### Requirements

The purpose of this assignment was to use multithreading to eliminate race conditions and build a functioning BankSim.
We implemented everything that was detailed in the instructions for this assignment. The following is what was implemented for each task:

Task 1: Provide a brief technical overview of the project, explain the major race condition that inhibits the successful execution of the Bank, and draw a UML sequence diagram which outlines the major race condition.

Task 2:

Task 3: Refactor the Bank class such that the testing method occurs on a separate thread from the transfer thread and implement mutual exclusion between the testing thread and the transferring thread. 

Task 4: 

Task 5: Implement a solution to the deadlock condition that blocks the successful termination of the BankSim after the simulation has reached the predefined number of transfers.

### Team Work


