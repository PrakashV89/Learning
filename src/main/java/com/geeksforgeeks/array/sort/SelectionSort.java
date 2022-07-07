package com.geeksforgeeks.array.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortWithTempArr(new int[]{1, 10, 9 , 2, 12})));
        System.out.println(Arrays.toString(sortBitBetter(new int[]{1, 10, 9 , 2, 12})));
    }

    private static int[] sortBitBetter(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int minIndex) {
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }

    private static int[] sortWithTempArr(int[] arr) {
        int[] tmp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int minInd = i;
            for (int j = 0; j < arr.length; j++) {
                if(arr[j] < arr[minInd]){
                    minInd = j;
                }
            }
            tmp[i] = arr[minInd];
            arr[minInd] = Integer.MAX_VALUE;
        }

        for(int i = 0 ;i < arr.length; i++){
            arr[i] = tmp[i];
        }

        return arr;
    }
}
