package com.udemyforbusiness.threads;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreProducerConsumerArrayDeque {
    private static int PRODUCER_THREADS = 4;
    private static int CONSUMER_THREADS = 4;

    private static class SharedObject {
        private ArrayDeque<Integer> item = new ArrayDeque<>();
        private ReentrantLock reentrantLock = new ReentrantLock();

        public ArrayDeque<Integer> getItem() {
            return item;
        }

        public ReentrantLock lock() {
            return reentrantLock;
        }
    }

    public static void main(String[] args) {
        Semaphore producer = new Semaphore(PRODUCER_THREADS);
        Semaphore consumer = new Semaphore(0);
        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        SharedObject sharedObject = new SharedObject();
        for (int i = 0; i < PRODUCER_THREADS; i++) {
            producers.add(new Producer(producer, consumer, sharedObject));
        }

        for (int i = 0; i < CONSUMER_THREADS; i++) {
            consumers.add(new Consumer(producer, consumer, sharedObject));
        }

        for (Consumer consumerT : consumers) {
            consumerT.start();
        }

        for (Producer producerT : producers) {
            producerT.start();
        }

    }

    private static class Producer extends Thread {
        private Semaphore producer;
        private Semaphore consumer;
        private SharedObject item;
        private ReentrantLock reentrantLock;

        public Producer(Semaphore producer, Semaphore consumer, SharedObject item) {
            this.producer = producer;
            this.consumer = consumer;
            this.item = item;
            this.reentrantLock = item.lock();
        }

        public void run() {
            try {
                producer.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int itemProduced = new Random().nextInt(100);
            reentrantLock.lock();
            item.getItem().offer(itemProduced);
            reentrantLock.unlock();
            System.out.println(itemProduced + " produced...");
            consumer.release();
        }
    }

    private static class Consumer extends Thread {
        private Semaphore producer;
        private Semaphore consumer;
        private SharedObject item;
        private ReentrantLock reentrantLock;

        public Consumer(Semaphore producer, Semaphore consumer, SharedObject item) {
            this.producer = producer;
            this.consumer = consumer;
            this.item = item;
            this.reentrantLock = item.lock();
        }

        public void run() {
            try {
                consumer.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            reentrantLock.lock();
            int itemConsumed = item.getItem().pop();
            System.out.println(itemConsumed + " consumed...");
            reentrantLock.unlock();
            producer.release();
        }
    }

}
