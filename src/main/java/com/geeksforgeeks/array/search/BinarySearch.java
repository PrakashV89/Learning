package com.geeksforgeeks.array.search;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(recursive(new int[]{10, 15}, 20));
        System.out.println(recursive(new int[]{10, 15}, 5));
        System.out.println(recursive(new int[]{10, 10}, 10));
        System.out.println(recursive(new int[]{10, 15}, 15));

        System.out.println(iterative(new int[]{10, 15}, 20));
        System.out.println(iterative(new int[]{10, 15}, 5));
        System.out.println(iterative(new int[]{10, 10}, 10));
        System.out.println(iterative(new int[]{10, 15}, 15));
    }

    private static int iterative(int[] arr, int num) {

        int start = 0;
        int end = arr.length-1;
        while(start <= end){
            int mid = (start + end)/2;

            if(arr[mid] == num){
                return mid;
            }

            if(arr[mid] > num){
                end = mid -1;
            }
            else{
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int recursive(int[] arr, int num) {
        return recursive(arr, num, 0, arr.length-1);
    }

    private static int recursive(int[] arr, int num, int start, int end){
        if(end < start || start > end){
            return -1;
        }

        if(end == start){
            return arr[end] == num ? end : -1;
        }

        int mid = (start+end)/2;

        if(arr[mid] == num){
            return mid;
        }


        if(arr[mid] > num){
            return recursive(arr, num, start, mid-1);
        }
        else{
            return recursive(arr, num, mid+1, end);
        }
    }
}
