package com.geeksforgeeks.array.frequencyorcounting;

public class FirstIndexOf1sInSortedArrayOf0sAnd1s {
    public static void main(String[] args) {
        binarySolution(new int[]{0, 0, 0, 1, 1, 1});
    }

    private static void binarySolution(int[] arr) {
        int firstIndex = firstIndex(arr, 1, 0, arr.length - 1);
        System.out.println(firstIndex);
    }

    private static int firstIndex(int[] arr, int elem, int low, int high) {
        if(low > high){
            return -1;
        }

        int mid = (low+high)/2;
        if((mid == 0 || arr[mid-1] < elem) && arr[mid] == elem){
            return mid;
        }

        if(elem > arr[mid]){
            return firstIndex(arr, elem, mid+1, high);
        }
        else{
            return firstIndex(arr, elem, low, mid - 1);
        }
    }
}
