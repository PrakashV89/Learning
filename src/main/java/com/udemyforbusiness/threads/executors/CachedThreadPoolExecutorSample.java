package com.udemyforbusiness.threads.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolExecutorSample {
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
                TimeUnit.SECONDS.timedWait(this,120);

                System.out.printf("Thread Name: %s || Runnable Task Id: %d is in terminated state" + System.lineSeparator(), Thread.currentThread().getId(), runId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new RunnableTask(i));
        }
    }
}
