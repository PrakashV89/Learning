package com.geeksforgeeks;

import java.util.Arrays;

public class LeftRotateAnArrayByD {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(performUsingReversalAlgorithm(new int[] { 1, 2, 3, 4, 5 }, 0)));
        System.out.println(Arrays.toString(performUsingReversalAlgorithm(new int[] { 1, 2, 3, 4, 5 }, 1)));
        System.out.println(Arrays.toString(performUsingReversalAlgorithm(new int[] { 1, 2, 3, 4, 5 }, 2)));
        System.out.println(Arrays.toString(performUsingReversalAlgorithm(new int[] { 1, 2, 3, 4, 5 }, 3)));
        System.out.println(Arrays.toString(performUsingReversalAlgorithm(new int[] { 1, 2, 3, 4, 5 }, 4)));
        System.out.println(Arrays.toString(performUsingReversalAlgorithm(new int[] { 1, 2, 3, 4, 5 }, 5)));
    }

    private static int[] leftRotateArrayByD(int[] arr, int d) {
        int[] tmp = new int[arr.length];
        int count = 0;

        for (int i = d; i < tmp.length; i++) {
            tmp[count++] = arr[i];
        }

        for (int i = 0; i < d; i++) {
            tmp[tmp.length - d + i] = arr[i];
        }

        return tmp;
    }

    private static int[] leftRotateArrayByDUsingAuxSpaceD(int[] arr, int d) {
        d = d % arr.length;
        /*
         * int i = 0;
         * 
         * int k = 0; int l = arr.length; while (i < arr.length) {
         * 
         * if (i >= d) {
         * 
         * k = i - d; l = i; } else { k = arr.length - d + i; l = i; } int tmp = arr[k];
         * arr[k] = arr[l]; arr[l] = tmp; System.out.println(i + ":" + k + ":" + l);
         * 
         * ++i; }
         * 
         * return arr;
         */

        int[] tmp = new int[d];
        for (int i = 0; i < d; i++) {
            tmp[i] = arr[i];
        }

        for (int i = d; i < arr.length; i++) {
            arr[i - d] = arr[i];
        }

        for (int i = 0; i < d; i++) {
            arr[arr.length - d + i] = tmp[i];
        }

        return arr;
    }

    /***
     * Reversal Algorithm Time Complexity : Theta(n) Auxillary Space : Theta(1)
     */
    private static int[] performUsingReversalAlgorithm(int[] arr, int d) {
        if (d == 0) {
            return arr;
        }
        reverse(arr, 0, d - 1);
        reverse(arr, d, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
        return arr;
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            ++start;
            --end;
        }
    }
}
