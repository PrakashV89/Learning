package com.udemyforbusiness.threads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorialThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {
        long[] factorialNums = new long[] { 0L, 23L, 3045L, 3066L, 3068L, 100000L };
        List<FactorialThread> threads = new ArrayList<>();

        for (long factorialNum : factorialNums) {
            threads.add(new FactorialThread(factorialNum));
        }

        for (FactorialThread factorialThread : threads) {
            // factorialThread.setDaemon(true);
            factorialThread.start();
        }

        for (FactorialThread factorialThread : threads) {
            factorialThread.join(2000);
            factorialThread.interrupt();
        }

        for (FactorialThread factorialThread : threads) {
            if (factorialThread.isComputationInterrupted()) {
                System.out.println("Factorial Thread computation interrupted for " + factorialThread.getFactorialNum());
                continue;
            }

            if (factorialThread.isFinished()) {
                System.out.println("Factorial Thread for " + factorialThread.getFactorialNum() + " is "
                        + factorialThread.getResult());
            } else {
                System.out.println("Factorial Thread for " + factorialThread.getFactorialNum() + " is in progress....");
            }
        }
    }

    private static class FactorialThread extends Thread {
        private long factorialNum;
        private BigInteger result = BigInteger.ONE;
        private boolean finished = false;
        private boolean computationInterrupted = false;

        protected FactorialThread(long factorialNum) {
            this.factorialNum = factorialNum;
        }

        public void run() {
            this.result = getFact(factorialNum);
            this.finished = true;
        }

        private BigInteger getFact(long factorialNum) {
            BigInteger result = BigInteger.ONE;
            for (int i = 2; i <= factorialNum; i++) {
                if (this.isInterrupted()) {
                    System.out.println("Factorial Thread for " + factorialNum + " is interrupted!");
                    this.computationInterrupted = true;
                    return BigInteger.ZERO;
                }
                result = result.multiply(BigInteger.valueOf(i));
            }

            return result;
        }

        public boolean isFinished() {
            return finished;
        }

        public BigInteger getResult() {
            return result;
        }

        public long getFactorialNum() {
            return factorialNum;
        }

        public boolean isComputationInterrupted() {
            return computationInterrupted;
        }
    }
}
