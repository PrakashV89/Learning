package com.udemyforbusiness.threads;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;

        PowerCalculatingThread powerCalculatingThread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread powerCalculatingThread2 = new PowerCalculatingThread(base2, power2);

        powerCalculatingThread1.start();
        powerCalculatingThread2.start();

        try {
            powerCalculatingThread1.join();
            powerCalculatingThread2.join();
        } catch (InterruptedException ie) {
            System.out.println("PowerCalculatingThread is interrupted!");
        }

        result = powerCalculatingThread1.getResult().add(powerCalculatingThread2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
        private boolean finished;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
            this.finished = true;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return finished;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ComplexCalculation().calculateResult(BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(2),
                BigInteger.valueOf(2)));
    }
}