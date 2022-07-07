package com.geeksforgeeks.array.frequencyorcounting;

public class CountOccurrencesOfXInSortedArr {
    public static void main(String[] args) {
        linearSolution(new int[]{1, 2, 3}, 3);
        linearSolution(new int[]{1, 2, 2, 3, 3, 4, 6}, 2);

        binarySolution(new int[]{1, 2, 3}, 3);
        binarySolution(new int[]{1, 2, 2, 3, 3, 4, 6}, 2);
    }

    private static void binarySolution(int[] arr, int elem) {
        System.out.println(binarySearch(arr, elem, 0 , arr.length-1));
    }

    private static int binarySearch(int[] arr, int elem, int low, int high){

        int firstIndex = firstIndex(arr, elem, low, high);
        int lastIndex = lastIndex(arr, elem, low, high);

        return lastIndex - firstIndex + 1;
    }

    private static int firstIndex(int[] arr, int elem, int low, int high) {
        if(low > high){
            return -1;
        }
        int mid = (low + high)/2;

        if((mid == 0 || arr[mid -1] < elem) && arr[mid] == elem){
            return mid;
        }

        if(elem > arr[mid]){
            return firstIndex(arr, elem, mid + 1, high);
        }
        else{
            return firstIndex(arr, elem, low, mid - 1);
        }
    }

    private static int lastIndex(int[] arr, int elem, int low, int high) {
        if(low > high){
            return -1;
        }
        int mid = (low+high)/2;
        if((mid == arr.length - 1 || arr[mid + 1] > elem) && arr[mid] == elem){
            return mid;
        }

        if(elem < arr[mid]){
            return lastIndex(arr, elem, low ,mid -1);
        }
        else{
            return lastIndex(arr, elem, mid + 1, high);
        }
    }



    private static void linearSolution(int[] arr, int elem) {
//        int count = 1;
//        for (int i = 1; i < arr.length; i++) {
//            if(arr[i-1] != arr[i]){
//                System.out.print(arr[i-1] + " : " + count + ", ");
//                count = 1;
//                continue;
//            }
//            ++count;
//        }
//        System.out.println(arr[arr.length-1] + " : " + count);

        int count = -1;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == elem && count == -1){
                count = 1;
                continue;
            }

            if(count > 0 && arr[i] == elem){
                ++count;
            }

            if(count > 0 && arr[i] != elem){
                break;
            }
        }

        System.out.println(count);
    }
}
