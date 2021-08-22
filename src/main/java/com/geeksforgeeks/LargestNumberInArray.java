package com.geeksforgeeks;

class LargestNumberInArray {
    public static void main(String[] args) {
        System.out.println(getIndexOfLargestValueInArrWithOneAuxillarySpace(new int[] { 10, 5, 20, 8 }));
        System.out.println(getIndexOfLargestValueInArrWithOneAuxillarySpace(new int[] { 40, 8, 50, 100 }));
    }

    /***
     * Time Complexity : O(n)
     */
    protected static int getIndexOfLargestValueInArrWithOneAuxillarySpace(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    /***
     * Time Complexity : O(n)
     */
    private static int getIndexOfLargestValueInArr(int[] arr) {
        int max = -1;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }

        return index;
    }

    /***
     * Time Complexity : O(n^2)
     */
    private static int getIndexOfLargestValueInArrTimeComplexityNSquared(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int maxIndex = -1;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    maxIndex = i;
                    break;
                }
            }
            if (maxIndex == -1) {
                return i;
            }
        }

        return -1;
    }
}