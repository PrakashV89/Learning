package com.geeksforgeeks;

public class MaximumDifferenceInArrJGtI {
    public static void main(String[] args) {
        System.out.println(getUsingTimeComplexityO_N(new int[] { 2, 3, 10, 6, 4, 8, 1 }));
    }

    private static int get(int[] arr) {
        int maxDiff = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - arr[i] > maxDiff) {
                    maxDiff = arr[j] - arr[i];
                }
            }
        }

        return maxDiff;
    }

    private static int getUsingTimeComplexityO_N(int[] arr) {
        int max = arr[1] - arr[0], min = arr[0];
        for (int j = 1; j < arr.length; j++) {
            max = Math.max(max, arr[j] - min);
            min = Math.min(min, arr[j]);
        }

        return max;
    }
}
