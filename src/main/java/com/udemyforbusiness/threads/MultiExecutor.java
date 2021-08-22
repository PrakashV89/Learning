package com.udemyforbusiness.threads;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here
    private List<Runnable> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            threads.add(new Thread(tasks.get(i)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}