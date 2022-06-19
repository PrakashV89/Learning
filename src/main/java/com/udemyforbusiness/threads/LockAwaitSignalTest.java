package com.conncurrent.packages.understanding;

class LockAwaitSignalTest{
    public static void main(String[] args){
        final java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
        final java.util.concurrent.locks.Condition condition = lock.newCondition();
        Thread a = new Thread(() -> {
            System.out.println("Thread A started");
            lock.lock();
            System.out.println("Thread A locked");
            try{
                System.out.println("Thread A entering await");
                condition.await();
                System.out.println("Thread A signal recieved");
            }
            catch(InterruptedException ie){
                //Do nothing for now
            }
            finally{
                lock.unlock();
                System.out.println("Thread A unlocked");
            }
        });

        Thread b = new Thread(() -> {
            System.out.println("Thread B started");
            lock.lock();
            System.out.println("Thread B locked");
            try{
                System.out.println("Thread B entering signal");
                condition.signal();
                System.out.println("Thread B signal sent");
            }
            finally{
                lock.unlock();
                System.out.println("Thread B unlocked");
            }
        });


        a.start();
        b.start();
    }

}