package com.udemyforbusiness.threads;

public class ResourceSharingBetweenThreadsIssues {
    public static void main(String[] args) throws java.lang.InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingCounterThread incrementingCounterThread = new IncrementingCounterThread(inventoryCounter);
        DecrementingCounterThread decrementingCounterThread = new DecrementingCounterThread(inventoryCounter);

        // Seuqential
        /*
         * incrementingCounterThread.start(); incrementingCounterThread.join();
         * 
         * decrementingCounterThread.start(); decrementingCounterThread.join();
         */

        incrementingCounterThread.start();
        decrementingCounterThread.start();

        incrementingCounterThread.join();
        decrementingCounterThread.join();

        System.out.println("Inventory Counter is " + inventoryCounter.getCounter());
    }

    private static abstract class CounterThread extends Thread {
        protected final InventoryCounter inventoryCounter;

        protected CounterThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            performOperation();
        }

        protected abstract void performOperation();
    }

    private static class IncrementingCounterThread extends CounterThread {

        protected IncrementingCounterThread(InventoryCounter inventoryCounter) {
            super(inventoryCounter);
        }

        protected void performOperation() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class DecrementingCounterThread extends CounterThread {

        protected DecrementingCounterThread(InventoryCounter inventoryCounter) {
            super(inventoryCounter);
        }

        protected void performOperation() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class InventoryCounter {
        private int counter;

        // Syncrhonized Block
        // public synchronized void increment() {
        // counter++;
        // }

        // public synchronized void decrement() {
        // counter--;
        // }

        // public synchronized int getCounter() {
        // return counter;
        // }
        // }

        private Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                counter++;
            }
        }

        public synchronized void decrement() {
            synchronized (lock) {
                counter--;
            }
        }

        public int getCounter() {
            synchronized (lock) {
                return counter;
            }
        }
    }

}
