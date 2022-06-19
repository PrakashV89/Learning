package com.udemyforbusiness.threads;

import static java.lang.System.out;

import java.math.BigInteger;

public class ThreadCoordination {
    public static void main(String[] args) {
        testBasicThreadInterrupt();
        // testThreadInterruptWithSpecificCheck();
    }

    private static void testBasicThreadInterrupt() {
        Thread sleepingThread = new Thread(() -> {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted!");
            }
        });

        // sleepingThread.setDaemon(true);
        sleepingThread.start();
        sleepingThread.interrupt();
    }

    private static void testThreadInterruptWithSpecificCheck() {
        Thread longComputingThread = new LongComputingThread(BigInteger.valueOf(2), BigInteger.valueOf(100000000));

        /*
         * Daemon Thread - TRUE - Background Activity - Not so critcal processing to the
         * application - Ex: File Saving Thread in Notepad Application running every few
         * mins FALSE - Main Thread will wait for it to complete
         */
        // longComputingThread.setDaemon(true);

        longComputingThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        longComputingThread.interrupt();
    }

    public static class LongComputingThread extends Thread {
        private BigInteger base;
        private BigInteger exponent;

        public LongComputingThread(BigInteger base, BigInteger exponent) {
            this.base = base;
            this.exponent = exponent;
        }

        @Override
        public void run() {
            BigInteger result = BigInteger.ONE;

            for (int i = 0; exponent.compareTo(BigInteger.valueOf(i)) != 0; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    out.println("Long Computation Thread Interrupted!");
                    return;
                }
                result = result.multiply(base);
            }

            out.println(base + "^" + exponent + " : " + result);
        }
    }
}
