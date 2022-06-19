package com.udemyforbusiness.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCounterAtomic {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                inventoryCounter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                inventoryCounter.decrement();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                inventoryCounter.add(2);
            }
        });

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("count is " + inventoryCounter.get());
    }

    public static class InventoryCounter {
        private final AtomicInteger counter = new AtomicInteger(0);

        public void increment() {
            this.counter.incrementAndGet();
        }

        public void decrement() {
            this.counter.decrementAndGet();
        }

        public void add(int delta) {
            this.counter.getAndAdd(delta);
        }

        public int get() {
            return counter.get();
        }
    }
}
