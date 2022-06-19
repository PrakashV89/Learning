package com.udemyforbusiness.threads;

class ObjectWaitNotifyTest{
    public static void main(String[] args){
        final Object lock = new Object();
        Thread a = new Thread(() -> {
            System.out.println("Thread A - Entering Lock");
            synchronized(lock){
                System.out.println("Thread A - Entering wait");
                try{
                    lock.wait();
                }
                catch(InterruptedException ie){

                }
                System.out.println("Thread A - notify recieved");
            }
        });

        Thread b = new Thread(() -> {
            //Critical Issue here because notify may be fired before Thread A
            // calls wait causing Thread A to be in WAITING state
            //java.util.concurrent.locks.LockSupport.parkNanos(1000);
            System.out.println("Thread B - Entering Lock");
            synchronized(lock){
                System.out.println("Thread B - Entering notify");
                lock.notify();
                System.out.println("Thread B - notify sent");
            }
        });

            a.start();
            b.start();

    }
}