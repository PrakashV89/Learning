package com.geeksforgeeks.array.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        auxArrUsingSortFuntion(new int[]{1, 2, 5, 10}, new int[]{12,15,15,19});
        auxArrUsingInefficient(new int[]{1, 2, 5, 10}, new int[]{3,4,7,9});
        efficientUsing3Params(new int[]{1, 2, 5, 10, 3,4,7,9},0, 3, 7);
        thethaNLogNAndAuxThetaN(new int[]{1, 2, 7, 8, 3,4,7,9});
    }

    private static void thethaNLogNAndAuxThetaN(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if(high > low){
            int mid = low + (high-low)/2;

            mergeSort(arr, low, mid);
            mergeSort(arr,mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] left = new int[mid - low + 1];
        int[] right = new int[high-mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[low+i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[i + mid + 1];
        }

        int i = 0, j = 0;
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                arr[low++] = left[i++];
            }
            else{
                arr[low++] = right[j++];
            }
        }

        while(i < left.length){
            arr[low++] = left[i++];
        }

        while(j < right.length){
            arr[low++] = right[j++];
        }
    }

    private static void efficientUsing3Params(int[] arr, int low, int mid, int high) {
        int i = 0;
        int j = 0;

        int[] left = new int[mid - low + 1];
        int[] right = new int[high - mid];


        for (int k = 0; k < left.length; k++) {
            left[k] = arr[k];
        }

        for(int k = 0; k < right.length; k++){
            right[k] = arr[mid+k+1];
        }

        int idx = 0;
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                arr[idx++] = left[i++];
            }
            else{
                arr[idx++] = right[j++];
            }
        }

        while (i < left.length){
            arr[idx++] = left[i++];
        }

        while (j < right.length){
            arr[idx++] = right[j++];
        }

        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] =  arr[j];
        arr[j] = temp;
    }

    private static void auxArrUsingInefficient(int[] arr1, int[] arr2) {
        int[] aux = new int[arr1.length+ arr2.length];
        int i=0, j=0, idx = 0;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                aux[idx++]= arr1[i++];
            }
            else{
                aux[idx++] = arr2[j++];
            }
        }

        while(i<= arr1.length-1){
            aux[idx++] = arr1[i++];
        }

        while(j<= arr2.length-1){
            aux[idx++] = arr2[j++];
        }


        System.out.println(Arrays.toString(aux));
    }

    private static void auxArrUsingSortFuntion(int[] arr1, int[] arr2) {
        int[] aux = new int[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            aux[i] = arr1[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            aux[arr1.length+i] = arr2[i];
        }

        Arrays.sort(aux);

        System.out.println(Arrays.toString(aux));
    }
}
