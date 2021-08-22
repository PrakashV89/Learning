package com.geeksforgeeks;

import static java.lang.System.out;

public class IsArraySorted {
    public static void main(String[] args) {
        out.println(isArraySortedUsingGFG(new int[] { 40, 20, 30 }));
        out.println(isArraySortedUsingGFG(new int[] { 40, 40, 40 }));
        out.println(isArraySortedUsingGFG(new int[] { 40 }));
    }

    /***
     * Time Complexity : O(n) - Linear
     * 
     * @param arr
     * @return
     */
    private static boolean isArraySorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }

        return true;
    }

    /***
     * Time Complexity : O(n^2) - Quadratic
     * 
     * @param arr
     * @return
     */
    private static boolean isArraySortedUsingGFG(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    return false;
                }
            }

        }

        return true;
    }
}
