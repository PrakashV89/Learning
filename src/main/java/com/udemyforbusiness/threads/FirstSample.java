package com.udemyforbusiness.threads;

public class FirstSample {
    private static Thread.UncaughtExceptionHandler ueh = new Thread.UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + " Thread: Exception Caught: " + e.getMessage());

        }

    };

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Galactus");
        // Thread.currentThread().setUncaughtExceptionHandler(ueh);

        Thread thread = new Thread(() -> {
            System.out.println("We are now in thread " + Thread.currentThread().getName());
            System.out
                    .println(Thread.currentThread().getName() + " priority is " + Thread.currentThread().getPriority());
            throw new RuntimeException("Internal Exception");
        });
        // thread.setUncaughtExceptionHandler(ueh);

        Thread.setDefaultUncaughtExceptionHandler(ueh);
        thread.setName("Herald");

        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " beofre starting a new thread");
        thread.start();
        System.out.println(
                "We are now in thread: " + Thread.currentThread().getName() + " after starting the new thread");
        throw new RuntimeException("Main Thread Exception Simulation");
        // Thread.sleep(10000);
    }
}
