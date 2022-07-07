package com.geeksforgeeks.array.partition;

public class Hoare {
    public static void main(String[] args) {
        int[] arr ={3, 5,4, 7, 7, 10, 9};
        int p = 6;

        System.out.println(partition(arr, p));
    }

    private static int partition(int[] arr, int p) {

        int low = 0;
        int high = arr.length;
        int i = low-1, j = high;

        while(true){
            do{
                i++;
            }while(arr[i] < arr[p]);

            do{
                j--;
            }while(arr[j] > arr[p]);

            if(i >= j) return  j;

            swap(arr, i, j);
        }
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
