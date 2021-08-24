package com.udemyforbusiness.threads;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GalactusSummonsHerald {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Main Thread");
        List<HeraldThread> heralds = new ArrayList<>();

        GalactusThread galactusThread = new GalactusThread();
        galactusThread.setName("Galactus is");

        galactusThread.start();

        String[] heraldNames = { "Silver Surfer", "Hulk", "Thor" };

        for (String heraldName : heraldNames) {
            HeraldThread herald = new HeraldThread(galactusThread, heralds);
            herald.setName(heraldName);
            heralds.add(herald);
        }

        for (HeraldThread herald : heralds) {
            herald.start();
        }

        // System.out.println("Started /!111");
        galactusThread.join();

        // System.out.println("Started /!111");

    }

    private static class GalactusThread extends Thread {
        private volatile HeraldThread herald;

        public void run() {
            while (Objects.isNull(herald)) {
                // System.out.println("Herald SUMMON");
            }
            System.out.println(herald.getName() + " Summoned!");
        }

        public void setHerald(HeraldThread heraldThread) {
            this.herald = heraldThread;
        }

        @Override
        protected void finalize() throws Throwable {
            // TODO Auto-generated method stub
            super.finalize();
            System.out.println(Thread.currentThread().getName());
        }

    }

    private static class HeraldThread extends Thread {
        private GalactusThread galactusThread;
        private List<HeraldThread> heralds;

        protected HeraldThread(GalactusThread galactusThread, List<HeraldThread> heralds) {
            this.galactusThread = galactusThread;
            this.heralds = heralds;
        }

        public void run() {
            try {
                Thread.sleep(new Random().nextInt(100));
                // out.println(this.getName() + " is arriving my lord Galactus!");
                for (HeraldThread herald : heralds) {
                    if (!herald.equals(this)) {
                        herald.interrupt();
                    } else {
                        galactusThread.setHerald(herald);
                    }
                }
            } catch (InterruptedException ie) {
                // out.println("Already another Herald has been summoned!");
            }
        }
    }
}
