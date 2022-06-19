package com.udemyforbusiness.threads.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuturesSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();
        List<Future> futuresForRunnable = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            futures.add(executorService.submit(new MyCallable(i + 1)));
            futuresForRunnable.add(executorService.submit(new MyRunnable((i + 1) * 10)));
        }

        for (Future future : futures) {
            System.out.println(future.get());
        }

        for (Future future : futuresForRunnable) {
            System.out.println(future.get());
        }
    }

    static class MyCallable implements Callable<String> {
        public int id;

        public MyCallable(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(10);
            return "Id : " + id;
        }

    }

    static class MyRunnable implements Runnable {
        public int id;

        public MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}