package com.udemyforbusiness.threads;

public class SecondSample {
    public static void main(String[] args) {
        Thread galactusThread = Thread.currentThread();
        galactusThread.setName("Galactus");

        HeraldThread.get("Silver Surfer", galactusThread).start();
        HeraldThread.get("Hulk", galactusThread).start();
        HeraldThread.get("Thor", galactusThread).start();

    }

    private static class HeraldThread extends Thread {
        private final String heraldName;
        private final Thread godThread;

        public HeraldThread(String heraldName, Thread godThread) {
            this.heraldName = heraldName;
            this.godThread = godThread;
        }

        public static HeraldThread get(String heraldName, Thread godThread) {
            return new HeraldThread(heraldName, godThread);
        }

        public void run() {
            System.out.println(
                    "I'm " + heraldName + ". I'm your herald. Here to serve! My sire - " + godThread.getName());
        }
    }
}
