package com.udemyforbusiness.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {
    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args){
        Livelock livelock = new Livelock();

        new Thread(livelock::worker1, "Thread-A").start();
        new Thread(livelock::worker2, "Thread-B").start();

    }

    private void worker1() {
        while(true) {
            try {
                if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " Lock - 1 : " + "Acquired");
                }

                Thread.sleep(100);
                if (lock2.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " Lock - 2 : " + "Acquired");
                    lock2.unlock();
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " Lock - 2 : " + "Unable to Acquire");
                }
            } catch (InterruptedException ie) {

            }
        }
    }

    private void worker2() {
        while(true){
            try {
                if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " Lock - 2 : " + "Acquired");
                }

                Thread.sleep(100);
                if (lock1.tryLock()){
                    System.out.println(Thread.currentThread().getName() + " Lock - 1 : " + "Acquired");
                    lock1.unlock();
                }
                else{
                    System.out.println(Thread.currentThread().getName() + " Lock - 1 : " + "Unable to Acquire");
                }
            }
            catch(InterruptedException ie){

            }
        }
    }
}
