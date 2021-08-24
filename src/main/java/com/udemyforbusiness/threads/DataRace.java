package com.udemyforbusiness.threads;

public class DataRace {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread thread1 = new Thread(() -> {
            while (true) {
                /*
                 * try { //Thread.sleep(1); } catch (InterruptedException ie) {
                 * 
                 * }
                 */
                sharedResource.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                /*
                 * try { //Thread.sleep(1); } catch (InterruptedException ie) {
                 * 
                 * }
                 */
                sharedResource.checkDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    private static class SharedResource {
        private int a = 0;
        private int b = 0;

        public void increment() {
            a++;
            b++;
        }

        public void checkDataRace() {
            if (a < b) {
                System.out.println("Data Race Happened!");
            }
        }
    }
}
