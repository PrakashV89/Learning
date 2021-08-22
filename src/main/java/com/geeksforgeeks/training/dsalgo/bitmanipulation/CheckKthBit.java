package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class CheckKthBit {
    public static void main(String[] args) {
        System.out.println(checkKthBit(500, 3));
    }

    private static boolean checkKthBit(int n, int k) {
        return ((n >> k) & 1) != 0;
    }
}
