package com.leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class DiningProblemSolutions {

    class DiningPhilosophersSemaphore {

        private AtomicReferenceArray<Semaphore> forks = new AtomicReferenceArray<>(new Semaphore[] { new Semaphore(1),
                new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1) });

        public DiningPhilosophersSemaphore() {

        }

        public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
                Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
            forks.get(philosopher).acquire();
            pickLeftFork.run();
            forks.get(philosopher == 0 ? 4 : philosopher - 1).acquire();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
            forks.get(philosopher == 0 ? 4 : philosopher - 1).release();
            forks.get(philosopher).release();

        }
    }

    class AtomicReferenceSolution {

        private AtomicReferenceArray<Integer> forks = new AtomicReferenceArray<>(new Integer[] { 0, 0, 0, 0, 0 });

        public AtomicReferenceSolution() {

        }

        public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
                Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
            int rightIndex = philosopher == 0 ? 4 : philosopher - 1;
            int leftIndex = philosopher == 3 ? rightIndex : philosopher;
            rightIndex = philosopher == 3 ? philosopher : rightIndex;
            synchronized (forks.get(leftIndex)) {
                pickLeftFork.run();
                synchronized (forks.get(rightIndex)) {
                    pickRightFork.run();
                    eat.run();
                    putLeftFork.run();
                    putRightFork.run();
                }
            }
        }

        class IntegerArraySolution {

            private Integer[] forks = new Integer[] { 0, 0, 0, 0, 0 };

            public IntegerArraySolution() {

            }

            public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
                    Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
                int rightIndex = philosopher == 0 ? 4 : philosopher - 1;
                int leftIndex = philosopher == 3 ? rightIndex : philosopher;
                rightIndex = philosopher == 3 ? philosopher : rightIndex;
                synchronized (forks[leftIndex]) {
                    pickLeftFork.run();
                    synchronized (forks[rightIndex]) {
                        pickRightFork.run();
                        eat.run();
                        putLeftFork.run();
                        putRightFork.run();
                    }
                }

            }
        }
    }

    class OptimumSolutionNotWorking {

        Semaphore[] arr = new Semaphore[5];

        public OptimumSolutionNotWorking() {
            for (int i = 0; i < 5; i++) {
                arr[i] = new Semaphore(1);
            }
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
                Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {

            int left = philosopher, right = (philosopher + 1) % 5;
            if (philosopher % 2 == 0) {
                arr[left].acquire();
                arr[right].acquire();
            } else {
                arr[right].acquire();
                arr[left].acquire();
            }

            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
            arr[left].release();
            arr[right].release();
        }

    }
}
