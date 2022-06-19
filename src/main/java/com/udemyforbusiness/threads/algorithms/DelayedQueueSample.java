package com.udemyforbusiness.threads.algorithms;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.lang.InterruptedException;

public class DelayedQueueSample {
    public static void main(String[] args){
        DelayQueue<Record> delayQueue = new DelayQueue<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Producer(delayQueue));
        executorService.execute(new Consumer(delayQueue));

        executorService.shutdown();

        try{
            if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        }
        catch(InterruptedException ie){

        }
    }


    private static class Record implements Delayed{
        private int id;
        private long delay;

        public Record(int id, long delay){
            this.id = id;
            this.delay = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Record record = (Record)o;

            long startTime = this.getDelay(TimeUnit.MILLISECONDS);
            long delay = record.getDelay(TimeUnit.MILLISECONDS);
            if(startTime < delay){
                return -1;
            }
            else if(startTime > delay){
                return 1;
            }
            else{
                return 0;
            }

        }

        public int getId(){
            return id;
        }
    }

    private static class Producer implements Runnable{
        private DelayQueue delayQueue;

        public Producer(DelayQueue<Record> delayQueue){
            this.delayQueue = delayQueue;
        }

        public void run(){
            for (int i = 1; i < 10; i++) {
                int delay = 100000/i;
                delayQueue.offer(new Record(i, delay));
                System.out.println("Record added to Delayed Queue, " + i + " - delay" + delay);
                try{
                    TimeUnit.SECONDS.sleep(1);
                }
                catch(InterruptedException ie){
                    System.out.println(Thread.currentThread().getId() + " Interrupted!");
                }
            }
        }
    }

    private static class Consumer implements Runnable{
        private DelayQueue<Record> delayQueue;

        public Consumer(DelayQueue delayQueue){
            this.delayQueue = delayQueue;
        }

        public void run(){
            while(true){
                try {
                    Record record = delayQueue.take();
                    System.out.println("Record " + record.getId() + " Retrieved! , Delay : " + record.getDelay(TimeUnit.MILLISECONDS));
                }
                catch(InterruptedException ie){
                    System.out.println(Thread.currentThread().getId() + " Interrupted!");
                }
            }
        }
    }
}
