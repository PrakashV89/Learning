package com.udemyforbusiness.threads;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

public class H2O {
    public static void main(String[] args) throws InterruptedException {
        H2O h2o = new H2O();
        char[] water = "OHOOHOOOHOHHOHHOOHHOHHHHHHOHHHHOHOHHOOOOHHOHHOHHHHHHHHHHHHHH".toCharArray();

        List<Thread> threads = new ArrayList<>();
        for (char molecule : water) {
            if (molecule == 'H') {
                threads.add(new Thread(() -> {
                    try {
                        h2o.hydrogen(() -> System.out.print("H"));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }));
            } else {
                threads.add(new Thread(() -> {
                    try {
                        h2o.oxygen(() -> System.out.print("O"));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }));
            }
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

    }

    Semaphore oxygenBarrier = new Semaphore(0);
    Semaphore barrier = new Semaphore(2);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        barrier.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        oxygenBarrier.release();

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        oxygenBarrier.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        barrier.release(2);

    }
}
