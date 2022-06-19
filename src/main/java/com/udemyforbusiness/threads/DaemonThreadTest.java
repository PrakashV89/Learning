package com.udemyforbusiness.threads;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread is running");

                try {
                    Thread.sleep(10000); // 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("Thread1");
        thread.setDaemon(true);
        thread.start();
    }
}
