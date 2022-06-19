package com.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolsExample {
    public static void main(String[] args) {
        System.out.println("Yo!");

        Executors.newFixedThreadPool(1);
    }
}
