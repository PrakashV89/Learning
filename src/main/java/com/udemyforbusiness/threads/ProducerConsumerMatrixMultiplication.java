package com.udemyforbusiness.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerMatrixMultiplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadSafeQueue threadSafeQueue = new ThreadSafeQueue();
        /*
         * Producer producer = new Producer(threadSafeQueue); Consumer consumer = new
         * Consumer(threadSafeQueue);
         * 
         * consumer.start(); producer.start();
         */

        Thread thread = new Thread(() -> System.out.println("Test"));
        thread.start();

        thread.join();
        thread.start();

    }

    private static class Matrices {
        private float[][] matrix1;
        private float[][] matrix2;

        public Matrices(float[][] matrix1, float[][] matrix2) {
            this.matrix1 = matrix1;
            this.matrix2 = matrix2;
        }

        public float[][] getMatrix1() {
            return matrix1;
        }

        public float[][] getMatrix2() {
            return matrix2;
        }
    }

    private static class ThreadSafeQueue {
        private Queue<Matrices> queue = new ArrayDeque<>();
        private ReentrantLock lock = new ReentrantLock();
        private boolean isEmpty = true;
        private boolean isTerminated = false;
        private long capacity = 5;

        public void addToQueue(Matrices matrices) {
            while (!isTerminated) {
                if (queue.size() == capacity) {
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                lock.lock();
                queue.offer(matrices);
                isEmpty = false;
                lock.unlock();
            }
        }

        public Matrices pop() {
            while (!isEmpty && !isTerminated) {
                lock.lock();
                Matrices matrices = queue.poll();
                if (queue.isEmpty()) {
                    isEmpty = true;
                    isTerminated = true;
                }
                lock.unlock();
                System.out.println("In Queue : " + queue.size());

                if (queue.size() == capacity - 1) {
                    synchronized (this) {
                        notifyAll();
                    }
                }
                return matrices;
            }
            return null;
        }

        public boolean isTerminated() {
            return isTerminated;
        }

        public boolean isEmpty() {
            return isEmpty;
        }
    }

    private static class Consumer extends Thread {
        private ThreadSafeQueue threadSafeQueue;
        private FileWriter fileWriter;

        public Consumer(ThreadSafeQueue threadSafeQueue) throws IOException {
            this.threadSafeQueue = threadSafeQueue;
            fileWriter = new FileWriter(new File("c:\\Users\\praka\\out\\matricess-multiplication"));
        }

        public void run() {
            while (!threadSafeQueue.isTerminated) {
                Matrices matrices = threadSafeQueue.pop();
                if (matrices != null && matrices.matrix1 != null && matrices.matrix2 != null) {
                    matrixMultiply(matrices);
                }
            }
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private void matrixMultiply(Matrices matrices) {
            float[][] matrix1 = matrices.matrix1;
            float[][] matrix2 = matrices.matrix2;
            float[][] resultMatrix = new float[matrix1.length][matrix2.length];
            for (int i = 0; i < resultMatrix.length; i++) {
                for (int j = 0; j < resultMatrix.length; j++) {
                    for (int k = 0; k < resultMatrix.length; k++) {
                        resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            for (int i = 0; i < resultMatrix.length; i++) {
                StringJoiner stringJoiner = new StringJoiner(",");
                for (int j = 0; j < resultMatrix.length; j++) {
                    stringJoiner.add(String.valueOf(resultMatrix[i][j]));
                }
                try {
                    fileWriter.write(stringJoiner.toString());
                    fileWriter.write("\n");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.write("\n");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private static class Producer extends Thread {
        private ThreadSafeQueue threadSafeQueue;
        int n = 10;

        public Producer(ThreadSafeQueue threadSafeQueue) {
            this.threadSafeQueue = threadSafeQueue;
        }

        public void run() {
            File file = new File("C:\\Users\\praka\\out\\matrices");
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            for (int n1 = 0; n1 < 100000; n1++) {
                float[][] matrix1 = new float[n][n];
                for (int i = 0; i < 10; i++) {
                    String line = "";
                    try {
                        line = bufferedReader.readLine();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String[] cols = line.split(",");
                    for (int j = 0; j < 10; j++) {
                        matrix1[i][j] = Float.valueOf(cols[j]);
                    }
                }
                try {
                    bufferedReader.readLine();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                float[][] matrix2 = new float[n][n];
                for (int i = 0; i < 10; i++) {
                    String line = "";
                    try {
                        line = bufferedReader.readLine();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String[] cols = line.split(",");
                    for (int j = 0; j < 10; j++) {
                        matrix2[i][j] = Float.valueOf(cols[j]);
                    }
                }
                try {
                    bufferedReader.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Matrices matrices = new Matrices(matrix1, matrix2);

                threadSafeQueue.addToQueue(matrices);
            }
        }

    }
}
