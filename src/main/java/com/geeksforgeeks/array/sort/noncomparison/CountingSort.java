package com.geeksforgeeks.array.sort.noncomparison;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 2, 4, 2, 9, 3};

        System.out.println("sorting with Array....");
        System.out.println("Output : " + Arrays.toString(sortWithArray(Arrays.copyOf(arr, arr.length))));

        System.out.println("sorting with Hashing....");
        System.out.println("Output : " + Arrays.toString(sortWithHashing(Arrays.copyOf(arr, arr.length))));
    }

    private static int[] sortWithArray(int[] arr) {
        System.out.println("Input : " + Arrays.toString(arr));
        int[] count = new int[Arrays.stream(arr).max().getAsInt() + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        int pos = 0;
        for (int i = 0; i < count.length; i++) {
            while(count[i] > 0){
                arr[pos++] = i;

                count[i]--;
            }
        }
        return arr;
    }

    private static int[] sortWithHashing(int[] arr) {
        System.out.println("Input : " + Arrays.toString(arr));
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            count.compute(arr[i], (k, v) -> v == null ? 1: ++v);
        }

        int pos = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            while (v > 0) {
                arr[pos++] = k;

                v--;
            }
        }
        return arr;
    }
}
