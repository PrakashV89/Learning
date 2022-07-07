package com.geeksforgeeks.array.frequencyorcounting;

public class FindPeakElementInUnSortedArray {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 15, 2, 23, 90, 67};

        naiveSolution(arr);
        binarySolution(arr);
    }

    private static void binarySolution(int[] arr) {
        System.out.println(binarySearch(arr, 0, arr.length-1));
    }

    private static int binarySearch(int[] arr, int low, int high) {
        if(low > high){
            return -1;
        }

        int mid = (low+high)/2;
        if((mid == 0 || (arr[mid] > arr[mid-1])) && (mid == arr.length-1 || (arr[mid] > arr[mid+1]))){
            return arr[mid];
        }
        if(mid > 0 && arr[mid -1] > arr[mid]){
            return binarySearch(arr, low , mid-1);
        }
        else{
            return binarySearch(arr, mid + 1, high);
        }
    }

    private static void naiveSolution(int[] arr) {

        for (int i = 1; i < arr.length-1; i++) {
            if(arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
