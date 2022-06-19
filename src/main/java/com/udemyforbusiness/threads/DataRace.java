package com.udemyforbusiness.threads;

public class DataRace {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedResource.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedResource.checkDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    private static class SharedResource {
        private volatile int a = 0;
        private volatile int b = 0;

        public void increment() {
            a++;
            b++;
        }

        public void checkDataRace() {
            if (b > a) {
                System.out.println("Data Race Happened!");
            }
        }
    }
}
