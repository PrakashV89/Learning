package com.udemyforbusiness.threads;

public class Quiz6 {
    public static void main(String[] args) {
        SharedClass sharedObject = new SharedClass();

        Thread thread1 = new Thread(() -> {
            while (true) {
                sharedObject.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                sharedObject.increment();
            }
        });

        thread1.start();
        thread2.start();
    }

    static class SharedClass {
        private int counter = 0;

        public synchronized void increment() {
            this.counter++;
        }
    }
}