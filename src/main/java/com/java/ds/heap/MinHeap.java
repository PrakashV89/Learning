package com.java.ds.heap;

import java.util.Arrays;

public class MinHeap {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 6, 5, 2, 1};

        heapify(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void heapify(int[] arr) {
        int lastNode = arr.length-1;
        int lastNonLeafNode = (lastNode-1)/2;
        for (int i = lastNonLeafNode; i >= 0; i--) {
            heapify(arr, i);
        }
        //inefficient approach
//        buildMinHeap(arr, 0);
    }

    private static void heapify(int[] arr, int index) {
        if(index >= arr.length){
            return;
        }

        int firstChildIndex = (2*index) + 1;
        int secondChildIndex = firstChildIndex + 1;

//inefficient Approach O(N*logN)
//        buildMinHeap(arr, firstChildIndex);
//        buildMinHeap(arr, secondChildIndex);

        int minVal = arr[index];
        int swapIndex = -1;

        if(firstChildIndex < arr.length){
            if(arr[firstChildIndex] < minVal){
                swapIndex = firstChildIndex;
                minVal = arr[firstChildIndex];
            }
        }

        if(secondChildIndex < arr.length){
            if(arr[secondChildIndex] < minVal){
                swapIndex = secondChildIndex;
            }
        }

        if(swapIndex != -1){
            swap(arr, index, swapIndex);
            heapify(arr, swapIndex);
        }



    }

    private static void swap(int[] arr, int index, int swapIndex) {
        int tmp = arr[index];
        arr[index] = arr[swapIndex];
        arr[swapIndex] = tmp;
    }
}
