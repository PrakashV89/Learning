package com.udemyforbusiness.threads.concurrentcollection;

import static java.lang.System.out;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListCollectionsSynchronized {
    public static void main(String[] args){
        Instant startTime = Instant.now();
        List<Integer> list = new ArrayList<>();
        //List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        //Using Anonymous Inner Class
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                for (int i = 0; i < 1000; i++) {
                    list.add(i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                for (int i = 0; i < 1000; i++) {
                    list.add(i);
                }
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException ie){
            Thread.currentThread().interrupt();
        }
        System.out.println("Time Taken : " + Duration.between(startTime, Instant.now()).toMillis());
        System.out.println("Array List Size : " + list.size());

    }
}
