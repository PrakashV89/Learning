package com.geeksforgeeks.array.sort.comparison;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        sort(new int[]{1, 2, 3 ,4});
        sort(new int[]{4, 3, 2, 1});
        sort(new int[]{1, 3, 2 ,4});
    }

    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i-1;
            while(j >= 0 && arr[j] > val){
                swap(arr, j+1, j);
                j--;
            }
            arr[j+1] = val;
        }

        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
}
