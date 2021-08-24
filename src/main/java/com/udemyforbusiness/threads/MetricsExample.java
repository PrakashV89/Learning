package com.udemyforbusiness.threads;

import java.util.Random;

public class MetricsExample {
    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        BusinessLogic businessLogic = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);
        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogic.start();
        businessLogic2.start();
        metricsPrinter.start();
    }

    private static class MetricsPrinter extends Thread {
        private Metrics metrics;

        protected MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {

                }
                System.out.println(metrics.getAverage());
            }
        }
    }

    private static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        protected BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        public void run() {
            while (true) {

                long startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(2));
                } catch (InterruptedException ie) {

                }
                long endTime = System.currentTimeMillis();

                metrics.addSample(endTime - startTime);
            }
        }
    }

    private static class Metrics {
        private long count;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
