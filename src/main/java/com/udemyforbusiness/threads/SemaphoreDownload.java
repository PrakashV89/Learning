package com.udemyforbusiness.threads;

import java.util.concurrent.Semaphore;

public class SemaphoreDownload {
    enum Download {
        ME;
        Semaphore download = new Semaphore(3);

        public void tryDownload() {
            try {
                download.acquire();
                System.out.println(Thread.currentThread().getName() + " - About to download");
                download();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void download() throws InterruptedException {
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName()  + " - Download Complete");
            download.release();
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(Download.ME::tryDownload).start();
        }
    }
}
