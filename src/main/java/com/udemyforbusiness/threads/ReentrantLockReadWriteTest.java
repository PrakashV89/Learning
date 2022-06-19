package com.udemyforbusiness.threads;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReentrantLockReadWriteTest {
    private static final long READER_THREADS = 100000L;

    public static void main(String[] args) {
        PulseStockPrice priceStock = new PulseStockPrice(Boolean.valueOf(args[0]));
        WriterThread writerThread = new WriterThread(priceStock);
        writerThread.setDaemon(true);
        writerThread.start();

        List<ReaderThread> readerThreads = new ArrayList<>((int) READER_THREADS);

        for (int i = 0; i < READER_THREADS; i++) {
            readerThreads.add(new ReaderThread(priceStock));
        }

        long startTime = System.currentTimeMillis();
        for (ReaderThread readerThread : readerThreads) {
            readerThread.setDaemon(true);
            readerThread.start();
        }

        for (ReaderThread readerThread : readerThreads) {
            try {
                readerThread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time Taken : " + (endTime - startTime));

    }

    public static class ReaderThread extends Thread {
        private PulseStockPrice pulseStockPrice;
        private Random random = new Random();

        public ReaderThread(PulseStockPrice pulseStockPrice) {
            this.pulseStockPrice = pulseStockPrice;
        }

        public void run() {
            Pulse randomPulse = Pulse.get(random.nextInt(1));
            pulseStockPrice.getStock(randomPulse);
            // System.out.println(randomPulse.name() + ":" +
            // pulseStockPrice.getStock(randomPulse));
        }

    }

    public static class PulseStockPrice {
        private ReentrantLock reentrantLock = new ReentrantLock();
        private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        private ReadLock readLock = reentrantReadWriteLock.readLock();
        private WriteLock writeLock = reentrantReadWriteLock.writeLock();

        private EnumMap<Pulse, Integer> pulsesStock = new EnumMap<>(Pulse.class);
        private Random random = new Random();
        private boolean useReentrantLock;

        public PulseStockPrice(boolean useReentrantLock) {
            this.useReentrantLock = useReentrantLock;
        }

        public void addStock() {
            Pulse randomPulse = Pulse.get(new Random().nextInt(1));
            getWriteLock().lock();
            try {
                pulsesStock.compute(randomPulse, (k, v) -> (v != null ? v : 0) + random.nextInt(1000));
            } finally {
                getWriteLock().unlock();
            }

        }

        private Lock getWriteLock() {
            if (useReentrantLock) {
                return reentrantLock;
            }
            return writeLock;
        }

        public void removeStock() {
            Pulse randomPulse = Pulse.get(new Random().nextInt(1));
            getWriteLock().lock();
            try {
                pulsesStock.compute(randomPulse, (k, v) -> {
                    int val = (v != null ? v : 0) - random.nextInt(1000);
                    return val > 0 ? val : 0;
                });
            } finally {
                getWriteLock().unlock();
            }

        }

        public Integer getStock(Pulse pulse) {
            getReadLock().lock();
            try {
                return pulsesStock.get(pulse);
            } finally {
                getReadLock().unlock();
            }
        }

        private Lock getReadLock() {
            if (useReentrantLock) {
                return reentrantLock;
            }
            return readLock;
        }

    }

    public static class WriterThread extends Thread {
        private PulseStockPrice pulseStockPrice;

        public WriterThread(PulseStockPrice pulseStockPrice) {
            this.pulseStockPrice = pulseStockPrice;
        }

        @Override
        public void run() {
            while (true) {
                pulseStockPrice.addStock();
                pulseStockPrice.removeStock();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private enum Pulse {
        NULL(-1), WHEAT(0), RICE(1);

        private int index;

        private Pulse(int index) {
            this.index = index;
        }

        public static Pulse get(int index) {
            for (Pulse pulse : Pulse.values()) {
                if (pulse.index == index) {
                    return pulse;
                }
            }

            return NULL;
        }
    }
}
