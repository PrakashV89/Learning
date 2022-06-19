package com.udemyforbusiness.threads.executors;

import java.util.concurrent.*;

public class ScheduledThreadPoolExecutorSample {
    static final int test;

    static {
      test = 1;

    }

    static class RunnableTask implements  Runnable{
        private final int runId;

        public RunnableTask(int runId){
            this.runId = runId;
        }

        public synchronized void run(){
            System.out.printf("Thread Name: %s || Runnable Task Id: %d is in running state" + System.lineSeparator(), Thread.currentThread().getId(), runId);
            try {
                TimeUnit.SECONDS.timedWait(this,3);

                System.out.printf("Thread Name: %s || Runnable Task Id: %d is in terminated state" + System.lineSeparator(), Thread.currentThread().getId(), runId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.scheduleAtFixedRate(new RunnableTask(i), 1, 1*i, TimeUnit.SECONDS);
        }
    }
}
