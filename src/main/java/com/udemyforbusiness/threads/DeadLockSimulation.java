package com.udemyforbusiness.threads;

import java.util.Random;

public class DeadLockSimulation {
    public static void main(String[] args) throws InterruptedException {
        Intersection intersection = new Intersection();
        TrainA trainA = new TrainA(intersection);
        TrainB trainB = new TrainB(intersection);
        Thread.sleep(60000);
        trainA.start();
        trainB.start();

    }

    private static class TrainB extends Thread {
        private Intersection intersection;
        Random random = new Random();

        protected TrainB(Intersection intersection) {
            this.intersection = intersection;
            this.setName("Train-B");
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException ie) {

                }

                intersection.takeRoadB();
            }
        }
    }

    private static class TrainA extends Thread {
        private Intersection intersection;
        Random random = new Random();

        protected TrainA(Intersection intersection) {
            this.intersection = intersection;
            this.setName("Train-A");
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(random.nextInt(5));
                } catch (InterruptedException ie) {

                }

                intersection.takeRoadA();
            }
        }
    }

    private static class Intersection {
        private Object roadA = new Object();
        private Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                synchronized (roadB) {
                    System.out.println("Road A - taken by " + Thread.currentThread().getName());
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadB) {
                synchronized (roadA) {
                    System.out.println("Road B - taken by " + Thread.currentThread().getName());
                }
            }
        }
    }
}
