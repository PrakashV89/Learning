package com.udemyforbusiness.threads.algorithms;

import java.util.concurrent.*;

public class CyclicBarrierSample {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("Barrier Broken because of worker Thread : " + Thread.currentThread().getName());
        });

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 6; i++) {
            executorService.execute(new Worker(i+1, cyclicBarrier));
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(50, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        }
        catch(InterruptedException ie){

        }

        System.out.println("Executor Services stopped....");
    }

    private static class Worker implements Runnable{
        private final int id;
        private final CyclicBarrier cyclicBarrier;

        public Worker(int id, CyclicBarrier cyclicBarrier){
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run(){
            try {
                TimeUnit.SECONDS.sleep(id);
                System.out.println("Worker " + id + " about to await");
                cyclicBarrier.await();
                System.out.println("Worker Executed for Id : " + id);
            }
            catch(InterruptedException | BrokenBarrierException ie){

            }
        }

        public int getId() {
            return id;
        }
    }
}
