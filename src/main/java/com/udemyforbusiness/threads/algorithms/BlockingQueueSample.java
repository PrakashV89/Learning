package com.udemyforbusiness.threads.algorithms;

import java.util.concurrent.*;

public class BlockingQueueSample {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Producer(blockingQueue));
        executorService.execute(new Consumer(blockingQueue));

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        }
        catch(InterruptedException ie){
            System.out.println("Executor Service Interrupted!");
        }
    }

    private static class Producer implements  Runnable{
        private BlockingQueue<Integer> blockingQueue;

        public Producer(BlockingQueue<Integer> blockingQueue){
            this.blockingQueue = blockingQueue;
        }

        public void run() {
            for (int i = 1; i <= 100_00_00; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println("Producer Enqueued : " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ie) {
                    System.out.println(Thread.currentThread().getId() + ":" + " Interrupted!");
                }
            }
        }
    }

    private static class Consumer implements Runnable{
        private BlockingQueue<Integer> blockingQueue;

        public Consumer(BlockingQueue<Integer> blockingQueue){
            this.blockingQueue = blockingQueue;
        }

        public void run(){
            while(true){
                try {
                    Integer dequeue = blockingQueue.take();
                    System.out.println("Dequeue : " + dequeue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
