package com.geeksforgeeks;

import java.util.Arrays;

public class SmallestPositiveInteger {
    public static void main(String[] args) {
        int[] arr = { 0, -10, 1, 3, -20 };
        System.out.println((int) get(arr));
    }

    private static int get1(int[] arr) {
        int[] hash = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                hash[arr[i] - 1]++;
            }
        }

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 0) {
                return i + 1;
            }
        }

        return -1;
    }

    private static int get(int[] arr) {
        int n = arr.length;
        // Check if 1 is present in array or not
        for (int i = 0; i < n; i++) {

            // Loop to check boundary
            // condition and for swapping
            System.out.println(Arrays.toString(arr));
            while (arr[i] >= 1 && arr[i] <= n && arr[i] != arr[arr[i] - 1]) {

                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
                System.out.println(Arrays.toString(arr));
            }
        }

        // Finding which index has value less than n
        for (int i = 0; i < n; i++)
            if (arr[i] != i + 1)
                return (i + 1);

        // If array has values from 1 to n
        return (n + 1);
    }
}
