package com.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MaximumSumOfSubArray {
    public static void main(String[] args) {
        System.out.println(getStaticUsingGFG_TC_ON(new int[] { 1, 2, 3, 4 }));
        System.out.println(getStaticUsingGFG_TC_ON(new int[] { 2, 3, -8, 7, -1, 2, 3 }));
    }

    private static int getOptimized(int[] arr) {
        var maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            maxSum = Math.max(maxSum, getOptimized(arr, i + 1, arr[i]));
        }

        return maxSum;
    }

    private static int getOptimized(int[] arr, int start, int sum) {
        var maxSum = 0;

        if (start == arr.length) {
            return sum;
        }
        maxSum = Math.max(maxSum, sum);
        maxSum = Math.max(maxSum, getOptimized(arr, start + 1, sum + arr[start]));

        return maxSum;

    }

    private static int get(int[] arr) {
        var maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            maxSum = Math.max(maxSum, get(arr, i + 1, Arrays.asList(arr[i])));
        }

        return maxSum;
    }

    private static int get(int[] arr, int start, List<Integer> list) {
        var maxSum = 0;

        if (start == arr.length) {
            System.out.println(list + ":" + getSumForList(list));
            return getSumForList(list);
        }

        List<Integer> list1 = new ArrayList<>(list);
        list1.add(arr[start]);
        System.out.println(list + ":" + getSumForList(list));
        maxSum = Math.max(maxSum, getSumForList(list));

        maxSum = Math.max(maxSum, get(arr, start + 1, list1));

        return maxSum;

    }

    private static int getSumForList(List<Integer> list) {
        Optional<Integer> sum = list.stream().reduce((i, j) -> i + j);
        return sum.isPresent() ? sum.get() : 0;
    }

    private static int getStaticUsingGFG_TC_O_NSquared(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sum = sum + arr[j];
                maxSum = Math.max(sum, maxSum);
            }
        }

        return maxSum;
    }

    private static int getStaticUsingGFG_TC_ON(int[] arr) {
        int sum[] = new int[arr.length];
        sum[0] = arr[0];
        int maxSum = sum[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = Math.max(sum[i - 1] + arr[i], arr[i]);
            maxSum = Math.max(maxSum, sum[i]);
        }

        return maxSum;
    }
}
