package com.geeksforgeeks;

public class MaximumKConsecutiveIntegersSlidingWindowTechnique {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 30, -5, 20, 7 };

        System.out.println((int) get(arr, 3));
    }

    // Sliding Window Technique
    private static int get(int[] arr, int k) {
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }

        int sum = maxSum;
        for (int i = k; i < arr.length; i++) {
            sum += arr[i] - arr[i - k];
            maxSum = Math.max(sum, maxSum);
        }

        return maxSum;
    }

    private static int get1(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i + k - 1 < arr.length; i++) {
            int sum = arr[i];
            for (int j = 1; j <= k && i + j < arr.length; j++) {
                sum += arr[i + j];
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
