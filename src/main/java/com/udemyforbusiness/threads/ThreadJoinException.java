package com.udemyforbusiness.threads;

public class ThreadJoinException {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            throw new java.lang.RuntimeException("Boo woo!");
        });

        t1.start();
        t1.join();

        System.out.println("das");
    }
}
