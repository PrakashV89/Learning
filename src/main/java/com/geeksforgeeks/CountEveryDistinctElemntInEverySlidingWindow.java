package com.geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

public class CountEveryDistinctElemntInEverySlidingWindow { // Counts distinct elements in window of size k

    public static void main(String args[]) {
        int arr[] = { 1, 2, 1, 3, 4, 2, 3 };
        int k = 4;
        countDistinct(arr, arr.length, k);
    }

    private static void countDistinct(int[] arr, int length, int k) {
        Map<Integer, Integer> distinctCountCounterMap = new HashMap<>();
        for (int j = 0; j < k; j++) {
            distinctCountCounterMap.compute(arr[j], (counter, counter1) -> counter1 == null ? 1 : ++counter1);
        }
        System.out.println(distinctCountCounterMap.size());
        for (int i = k; i < length; i++) {
            distinctCountCounterMap.compute(arr[i - k], (counter, counter1) -> {
                return counter1 != null && counter1 - 1 == 0 ? null : (counter1 == null ? null : --counter1);
            });
            for (int j = i; j <= k; j++) {
                distinctCountCounterMap.compute(arr[j], (counter, counter1) -> counter1 == null ? 1 : ++counter1);
            }
            System.out.println(distinctCountCounterMap.size());
        }
    }

    private static void countDistinct1(int[] arr, int n, int k) {

        for (int i = 0; i <= n - k; i++) {
            countDistinctInCurrentWindow(arr, i, k);
        }
    }

    private static void countDistinctInCurrentWindow(int[] arr, int i, int k) {
        int distinctCount = 0;
        for (int j = i; j < i + k; j++) {
            int j2;
            for (j2 = i; j2 < j; j2++) {
                if (arr[j] == arr[j2]) {
                    break;
                }
            }
            if (j2 == j) {
                distinctCount++;
            }
        }

        System.out.println(distinctCount);
    }
}
