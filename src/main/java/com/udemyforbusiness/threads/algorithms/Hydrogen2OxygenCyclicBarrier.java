package com.udemyforbusiness.threads.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Hydrogen2OxygenCyclicBarrier {
    //volatile int i = 1;
    Runnable oxygenRunnable = () -> System.out.println("In Oxygen Runnable");
    Semaphore semaphore = new Semaphore(0);
    static Semaphore h = new Semaphore(0);

    CyclicBarrier oxygenCyclicBarrier = new CyclicBarrier(1, oxygenRunnable);
    Phaser phaser = new Phaser(1);
    Runnable hydrogenRunnable = () -> {
        //System.out.println("In Hydrogen Runnable");
        //phaser.arrive();
        //System.out.println(phaser.getPhase());
        semaphore.release();
    };
    CyclicBarrier hydrogenCyclicBarrier = new CyclicBarrier(2, hydrogenRunnable);
    public static void main(String[] args) throws InterruptedException {
        char[] h2o = "OOHHHHHHHHHHHHHOO".toCharArray();
        Hydrogen2OxygenCyclicBarrier hydrogen2OxygenCyclicBarrier = new Hydrogen2OxygenCyclicBarrier();
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future> future = new ArrayList<>();

/*
        Semaphore h = new Semaphore(2);
        Semaphore o = new Semaphore(1);*/




        for (int i = 0; i < h2o.length; i++) {
            if(h2o[i] == 'H'){
                future.add(executor.submit(() -> {
                    try {
                        hydrogen2OxygenCyclicBarrier.hydrogen(()->System.out.println("H"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }
            else{
                future.add(executor.submit(() -> {
                    try {
                        hydrogen2OxygenCyclicBarrier.oxygen(()->System.out.println("O"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }

        }

        h.release(2);

        executor.shutdown();


        if(!executor.awaitTermination(10, TimeUnit.SECONDS)){
            executor.shutdownNow();
        }

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        try{
            //System.out.println("In Hydrogen");
           h.acquire();
             //System.out.println("Cylic Barrier signlled us");
            releaseHydrogen.run();
            semaphore.release();

        }catch(Exception e){

        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        try{
            semaphore.acquire(2);
            //System.out.println("In Oxygen");
            //System.out.println("Oxygen Cylic Barrier signlled us");
            releaseOxygen.run();
            h.release(2);
            //phaser.awaitAdvance(i++);
        }catch(Exception e){

        }
    }
}
