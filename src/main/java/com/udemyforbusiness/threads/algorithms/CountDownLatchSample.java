package com.udemyforbusiness.threads.algorithms;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSample {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i <5; i++) {
            executorService.execute(new Worker(i+1, countDownLatch));
        }

        try {
            countDownLatch.await();
        }
        catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        }
        System.out.println("All the prerequisites are dome");
        executorService.shutdown();
    }


    private static class Worker extends Thread{
        private int id;
        private CountDownLatch countDownLatch;

        public Worker(int id, CountDownLatch countDownLatch){
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        public void run(){
            doWork();
            countDownLatch.countDown();
        }

        private void doWork() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Worker %d - Work complete%s", id, System.lineSeparator());
        }
    }
}
