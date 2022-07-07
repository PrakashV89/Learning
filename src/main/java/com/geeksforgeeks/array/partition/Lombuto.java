package com.geeksforgeeks.array.partition;

import java.util.Arrays;

public class Lombuto {
    public static void main(String[] args) {
        int[] arr ={3, 5,4, 7, 7, 9};
        int p = 2;

        System.out.println(partition(arr, p));
    }

    private static int partition(int[] arr, int p) {


        swap(arr, arr.length-1, p);

        int low = 0;
        int high = arr.length-1;
        int i= low-1;
        int j = 0;
        p = high;

        while(i <= j && j < high){
            if(arr[j] < arr[p]){
                i++;
                swap(arr, j, i);
            }
            j++;
        }
        swap(arr, i+1, p);

        return i+1;
    }

    private static void swap(int[] arr, int i, int j) {
        if(i == j){
            return;
        }
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
}
