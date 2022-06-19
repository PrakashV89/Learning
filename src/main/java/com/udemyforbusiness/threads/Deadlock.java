package com.udemyforbusiness.threads;

import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    ReentrantLock lock1 = new ReentrantLock();
    ReentrantLock lock2 = new ReentrantLock();
    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        new Thread(deadlock::worker1, "Thread-A").start();
        new Thread(deadlock::worker2, "Thread-A").start();
    }

    public void worker1(){
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 1 - " + " acquired");
        lock2.lock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 2 - " + " acquired");

        lock1.unlock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 1 - " + " unlocked");
        lock2.unlock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 2 - " + " unlocked");
    }

    public void worker2(){
        lock2.lock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 2 - " + " acquired");
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 1 - " + " acquired");

        lock1.unlock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 1 - " + " unlocked");
        lock2.unlock();
        System.out.println(Thread.currentThread().getName() + "-" + "Lock 2 - " + " unlocked");
    }
}
