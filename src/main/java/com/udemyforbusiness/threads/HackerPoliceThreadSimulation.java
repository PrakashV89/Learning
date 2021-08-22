package com.udemyforbusiness.threads;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class HackerPoliceThreadSimulation {
    public static void main(String[] args) {
        Vault vault = new Vault();

        List<Thread> threads = new ArrayList<>();
        threads.add(new PoliceThread());
        threads.add(new AscendingHackerThread("Neo", vault));
        threads.add(new DescendingHackerThread("Morpheus", vault));

        for (Thread thread : threads) {
            thread.start();
        }
    }

    static class Vault {
        private int password;

        protected Vault() {
            password = new Random().ints(1, 100).findAny().getAsInt();
        }

        protected boolean guess(int guessedPassword) {
            return this.password == guessedPassword;
        }

    }

    static abstract class HackerThread extends Thread {
        protected final String hackerName;
        protected final Vault vault;

        protected HackerThread(String hackerName, Vault vault) {
            this.hackerName = hackerName;
            this.vault = vault;
        }

    }

    static class AscendingHackerThread extends HackerThread {
        protected AscendingHackerThread(String hackerName, Vault vault) {
            super(hackerName, vault);
        }

        public void run() {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (vault.guess(i)) {
                    out.println(hackerName + " has succesfully guessed the password. We have been HACKED!");
                    System.exit(1);
                }
            }
        }
    }

    static class DescendingHackerThread extends HackerThread {
        protected DescendingHackerThread(String hackerName, Vault vault) {
            super(hackerName, vault);
        }

        public void run() {
            for (int i = 100; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (vault.guess(i)) {
                    out.println(hackerName + " has succesfully guessed the password. We have been HACKED!");
                    System.exit(1);
                }
            }
        }
    }

    static class PoliceThread extends Thread {
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(i);
            }

            out.println("Game Over!. Hackers :-|");
            System.exit(1);
        }
    }
}
