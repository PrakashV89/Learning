package com.geeksforgeeks.array.sort.comparison.partition;

public class Naive {
    public static void main(String[] args) {
        int[] arr ={3, 5,10, 7, 7, 9};
        int p = 1;

        System.out.println(partition(arr, p));
    }

    private static int partition(int[] arr, int p) {
        int low = 0, high = arr.length-1;
        int res = -1;
        int[] temp = new int[high - low + 1];
        int idx = 0;
        for (int i = low; i <= high; i++) {
            if(arr[i] < arr[p]) {
                temp[idx++] = arr[i];
            }
        }

        for (int i = low; i <= high ; i++) {
            if(arr[i] == arr[p]){
                temp[idx++] = arr[i];
            }
        }
        res = low + idx - 1;

        for (int i = low; i <= high ; i++) {
            if(arr[i] > arr[p]){
                temp[idx++] = arr[i];
            }
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp[i-low];
        }

        return res;
    }
}
