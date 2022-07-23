package com.geeksforgeeks.array.sort.noncomparison;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 2, 4, 2, 9, 3};

        System.out.println("sorting with Array....");
        System.out.println("Output : " + Arrays.toString(sortWithArrayPositiveNumbers(Arrays.copyOf(arr, arr.length))));

        arr = new int[]{10, 2, 4, 2, 9, 3, -1};

        System.out.println("sorting with Array....");
        System.out.println("Output : " + Arrays.toString(sortWithArrayNegativeNumbers(Arrays.copyOf(arr, arr.length))));


        System.out.println("sorting with Hashing....");
        System.out.println("Output : " + Arrays.toString(sortWithHashing(Arrays.copyOf(arr, arr.length))));
    }

    private static int[] sortWithArrayNegativeNumbers(int[] arr) {
        if(arr.length <= 1){
            return arr;
        }

        int[] output = new int[arr.length];

        int minRange = arr[0];
        int maxRange = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < minRange)
                minRange = arr[i];
            if(arr[i] > maxRange)
                maxRange = arr[i];
        }

        int range = maxRange - minRange + 1;
        int[] count = new int[range];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - minRange]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        for (int i = 0; i < arr.length; i++) {
            final int idx = arr[i] - minRange;
            final int output_idx = count[idx] -1;
//            System.out.println("arr[i] : " + arr[i]);
//            System.out.println("idx : " + idx);
//            System.out.println("output_idx : " + output_idx);
            if(output_idx >= 0) {
                output[output_idx] = arr[i];
                count[idx]--;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }


        return arr;
    }

    private static int[] sortWithArrayPositiveNumbers(int[] arr){
        int[] output = new int[arr.length];
        int[] count = new int[10 + 1];
         for (int i = 0; i < arr.length; i++) {
             count[arr[i]]++;
         }

         for (int i = 1; i < count.length; i++) {
             count[i] += count[i-1];//calculating prefix
         }

         for (int i = 0; i < arr.length; i++) {
             if(count[arr[i]] > 0) {
                 output[count[arr[i]] - 1] = arr[i];
                 count[arr[i]]--;
             }
         }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
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
