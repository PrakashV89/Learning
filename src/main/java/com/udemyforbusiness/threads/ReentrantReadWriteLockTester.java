package com.udemyforbusiness.threads;

public class ReentrantReadWriteLockTester {
    public static void main(String[] args) throws InterruptedException {
        String[] test = { "true" };

        for (int i = 0; i <= 10; i++) {
            ReentrantLockReadWriteTest.main(test);
        }

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.gc();
        Thread.sleep(10000);
        test = new String[] { "false" };

        for (int i = 0; i <= 10; i++) {
            ReentrantLockReadWriteTest.main(test);
        }
    }
}
