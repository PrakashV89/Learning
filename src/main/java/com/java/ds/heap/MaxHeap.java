package com.java.ds.heap;

import java.util.Arrays;

public class MaxHeap {
    public static void main(String[] args) {
        int[] arr =new int[]{1, 10, 5, 2,6};

        heapify(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void heapify(int[] arr) {
        int lastNonLeafNode = (arr.length-2)/2;
        for(int i = lastNonLeafNode; i >=0 ; i--){
            heapify(arr, i);
        }
    }

    private static void heapify(int[] arr, int index) {
        if(index >= arr.length){
            return;
        }
        final int firstChild = (2*index) + 1;
        final int secondChild = firstChild + 1;
        System.out.printf("Children for %d : are %d and %d \n", arr[index] , firstChild <arr.length ? arr[firstChild] : -1, secondChild <arr.length ? arr[secondChild] : -1);
        int max = arr[index];
        boolean swapFirst = false;
        if(firstChild < arr.length){
            if(arr[firstChild] > max){
                swapFirst = true;
                max = arr[firstChild];
            }
        }

        if(secondChild < arr.length){
            if(arr[secondChild] > max){
                swapFirst = false;
                max = arr[secondChild];
            }
        }

        if(max != arr[index]){
            if(swapFirst) {
                swap(arr, index, firstChild);
                heapify(arr, firstChild);
            }
            else{
                swap(arr, index, secondChild);
                heapify(arr, secondChild);
            }
        }

    }

    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
