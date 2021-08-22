package com.geeksforgeeks;

public class PrefixSumTechnique {
    private static int[] PREFIX_SUM;

    public static void main(String[] args) {
        int[] arr = { 2, 8, 3, 9, 6, 5, 4 };
        precalculatePrefixSum(arr);
        System.out.println(get(0, 2));
        System.out.println(get(1, 3));
        System.out.println(get(2, 6));
    }

    private static void precalculatePrefixSum(int[] arr) {
        PREFIX_SUM = new int[arr.length];
        PREFIX_SUM[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            PREFIX_SUM[i] = PREFIX_SUM[i - 1] + arr[i];
        }
    }

    private static int get(int i, int j) {
        return i == 0 ? PREFIX_SUM[j] : PREFIX_SUM[j] - PREFIX_SUM[i - 1];
    }
}
