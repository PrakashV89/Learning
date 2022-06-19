package com.udemyforbusiness.threads;

import java.util.Random;

public class SynchronizedLockExceptionTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedClass synchronizedObject = new SynchronizedClass();
        Thread t = new Thread(() -> {
            try {
                synchronizedObject.testIncrement();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        t.start();
        t.interrupt();
        t.join();
    }

    public static class SynchronizedClass {
        int testVar = 0;

        public synchronized void testIncrement() throws InterruptedException {
            Thread.sleep(new Random(1).nextInt(50));
            throw new RuntimeException("Yo I'm Testing Here!");
        }
    }
}
