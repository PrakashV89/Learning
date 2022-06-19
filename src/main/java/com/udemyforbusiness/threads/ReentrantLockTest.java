package com.udemyforbusiness.threads;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Runnable target = () -> {
            printLockStats(reentrantLock);
            reentrantLock.lock();
            printLockStats(reentrantLock);
            reentrantLock.unlock();
            printLockStats(reentrantLock);
            boolean lockAcquired = reentrantLock.tryLock();
            out.println("Lock Acquired by tryLock : " + lockAcquired);
            printLockStats(reentrantLock);
            if (reentrantLock.isHeldByCurrentThread()) {
                System.out.println("Proceed with unlocking .....");
                reentrantLock.unlock();
            }
            // reentrantLock.unlock();
            printLockStats(reentrantLock);
            try {
                reentrantLock.lockInterruptibly();

            } catch (InterruptedException e) { // TODO Auto-generated catch block
                out.println("Lock Interrupt...");
            }

            out.println("##Thread execution completed for Thread : " + Thread.currentThread().getName());
        };
        Thread thread = new Thread(target);

        thread.setDaemon(true);
        thread.start();

        Thread thread1 = new Thread(target);

        thread1.setDaemon(true);
        thread1.start();

        try (Scanner scanner = new Scanner(System.in)) {
            while (!scanner.nextLine().equals("q")) {
                out.println("Press 'q' to quit...");
                try {
                    out.println("Queue Length : " + reentrantLock.getQueueLength());
                    out.println("Thread in Queue : " + reentrantLock.hasQueuedThread(thread));
                    out.println("Thread 1 in Queue : " + reentrantLock.hasQueuedThread(thread1));
                    out.println("Hold Count : " + reentrantLock.getHoldCount());
                    thread.interrupt();
                    thread1.interrupt();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private static void printLockStats(ReentrantLock reentrantLock) {
        out.println("Is held By Current Thread : " + reentrantLock.isHeldByCurrentThread());
        out.println("Is Locked ? : " + reentrantLock.isLocked());
        out.println("Has Queued Threads : " + reentrantLock.hasQueuedThreads());
    }
}
