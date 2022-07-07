package com.geeksforgeeks.array.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{1, 10, 9 , 2, 12})));
        System.out.println(Arrays.toString(sortBitBetter(new int[]{1, 10, 9 , 2, 12})));
    }

    private static int[] sort(int[] arr) {

        for(int i = 0; i < arr.length; i++){
            for(int j=1; j < arr.length-i; j++){
                if(arr[j-1] > arr[j]){
                    swap(arr, j-1, j);
                }
            }
        }

        return arr;
    }

    private static int[] sortBitBetter(int[] arr) {

        for(int i = 0; i < arr.length; i++){
            boolean swapped = false;
            for(int j=1; j < arr.length-i; j++){
                if(arr[j-1] > arr[j]){
                    swap(arr, j-1, j);
                    swapped = true;
                }
            }
            
            if(!swapped){
                break;
            }
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
}
