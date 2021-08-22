package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class PigeonHoleProblem {
    public static void main(String[] args) {
        System.out.println(findMissingNumberFromArr(new int[]{1, 2, 3, 4}));
    }

    private static int findMissingNumberFromArr(int[] is) {
        int num = 0;
        for (int i : is) {
            num ^= i;
        }
        for (int i = 1; i < is.length + 1; i++) {
            num ^= i;
        }
        return num;
    }
}
