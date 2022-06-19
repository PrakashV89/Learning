package com.udemyforbusiness.threads;

public class ThreadStateCheck {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        Thread t1 = new Thread(() -> {
            try {
                sharedObject.increment();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                sharedObject.increment();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    private static final class SharedObject {
        private volatile int i;

        public synchronized void increment() throws InterruptedException {
            ++i;
            Thread.sleep(100000);
        }
    }
}
