package com.leetcode.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzzFizzBuzz {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final FizzBuzz fizzBuzz = new FizzBuzz(15);
            Thread t1 = new Thread(() -> {
                try {
                    fizzBuzz.fizz(() -> System.out.println("Fizz"));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    fizzBuzz.buzz(() -> System.out.println("Buzz"));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            Thread t3 = new Thread(() -> {
                try {
                    fizzBuzz.fizzbuzz(() -> System.out.println("FizzBuzz"));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            Thread t4 = new Thread(() -> {
                try {
                    fizzBuzz.number((j) -> {
                        System.out.println(j);
                    });
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

            t1.setName("Fizz");
            t2.setName("Buzz");
            t3.setName("FizzBuzz");
            t4.setName("Number");

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();

            System.out.println("#####################################");
        }
    }

    private static class FizzBuzz {
        private int n;
        Semaphore number = new Semaphore(1);
        Semaphore fizz = new Semaphore(0);
        Semaphore buzz = new Semaphore(0);
        Semaphore fizzBuzz = new Semaphore(0);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 3; i <= n; i += 3) {
                if (i % 5 != 0) {
                    fizz.acquire();
                    printFizz.run();
                    number.release();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 5; i <= n; i += 5) {
                if (i % 3 != 0) {
                    buzz.acquire();
                    printBuzz.run();
                    number.release();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 15; i <= n; i += 15) {
                fizzBuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }

        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                number.acquire();
                if (i % 15 == 0) {
                    fizzBuzz.release();
                } else if (i % 3 == 0) {
                    fizz.release();
                } else if (i % 5 == 0) {
                    buzz.release();
                } else {
                    printNumber.accept(i);
                    number.release();
                }
            }
        }
    }
}
