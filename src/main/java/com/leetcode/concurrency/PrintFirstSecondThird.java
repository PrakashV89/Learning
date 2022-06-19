package com.leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintFirstSecondThird {

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        Thread t1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTime = System.currentTimeMillis();

        System.out.printf("Time Taken in ms : %d", endTime - startTime);

    }

    static class Foo {
        private Semaphore first = new Semaphore(1);
        private Semaphore second = new Semaphore(0);
        private Semaphore third = new Semaphore(0);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            first.acquire();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            second.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            second.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            third.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            third.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            first.release();
        }
    }

}
