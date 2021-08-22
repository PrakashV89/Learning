package com.geeksforgeeks;

import static java.lang.System.out;

public class SecondLargestNumberIndexInArray {
    public static void main(String[] args) {
        out.println(getSecondLargestNumberIndexInArrUsingGFG(new int[] { 10, 20, 20, 5, 6, 25, 30 }));
        out.println(getSecondLargestNumberIndexInArrUsingGFG(new int[] { 10, 10, 10, 2 }));
    }

    /***
     * Time Complexity : O(n) - Linear
     * 
     * @param arr
     * @return
     */
    private static int getSecondLargestNumberIndexInArr(int[] arr) {
        int maxIndex = -1;
        int secondLargestMaxIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (maxIndex == -1) {
                maxIndex = i;
            } else {
                if (arr[i] > arr[maxIndex]) {
                    secondLargestMaxIndex = maxIndex;
                    maxIndex = i;
                }
            }
        }

        out.println(maxIndex);
        return secondLargestMaxIndex;
    }

    /***
     * Time Complexity : O(n) - Linear
     * 
     * @param arr
     * @return
     */
    private static int getSecondLargestNumberIndexInArr1(int[] arr) {
        int maxIndex = LargestNumberInArray.getIndexOfLargestValueInArrWithOneAuxillarySpace(arr);
        int secondLargestMaxIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr[maxIndex] && (secondLargestMaxIndex == -1 || arr[i] > arr[secondLargestMaxIndex])) {
                secondLargestMaxIndex = i;
            }
        }

        out.println(maxIndex);
        return secondLargestMaxIndex;
    }

    private static int getSecondLargestNumberIndexInArrUsingGFG(int[] arr) {
        int maxIndex = 0;
        int secondLargestMaxIndex = -1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                secondLargestMaxIndex = maxIndex;
                maxIndex = i;
            } else if (arr[i] != arr[maxIndex]) {
                if (secondLargestMaxIndex == -1 || arr[i] > arr[secondLargestMaxIndex]) {
                    secondLargestMaxIndex = i;
                }

            }
        }

        out.println(maxIndex);
        return secondLargestMaxIndex;
    }
}
