package com.geeksforgeeks;

import static java.lang.System.out;

import java.util.Arrays;

public class ReverseAnArray {
    public static void main(String[] args) {
        out.println(Arrays.toString(reverseArrayUsingGFG(new int[] { 1, 2, 3, 4 })));
        out.println(Arrays.toString(reverseArrayUsingGFG(new int[] { 1, 2, 6, 3, 4 })));
    }

    /***
     * Time Complexity : Theta(n) - Linear Auxillary Space : Theta(1) - Constant
     * 
     * @param arr
     * @return int[]
     */
    private static int[] reverseArray(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < arr.length / 2) {

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;

            ++i;
            --j;
        }

        return arr;
    }

    /***
     * Time Complexity : Theta(n) - Linear Auxillary Space : Theta(1) - Constant
     * 
     * @param arr
     * @return int[]
     */
    private static int[] reverseArrayUsingGFG(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;

            ++i;
            --j;
        }

        return arr;
    }
}
