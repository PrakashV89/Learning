package com.geeksforgeeks;

public class EquilibriumPointPrefixSum {
    public static void main(String[] args) {
        int[] arr = { 3, 4, 8, -9, 20, 6 };
        System.out.println(efficientUsingONTimeComplexityO1AuxSpace(arr));
        System.out.println(efficientUsingONTimeComplexityO1AuxSpace(new int[] { 4, 2, -2 }));
        System.out.println(efficientUsingONTimeComplexityO1AuxSpace(new int[] { 2, -2, 4 }));
        System.out.println(efficientUsingONTimeComplexityO1AuxSpace(new int[] { 2, -2, 4, -1 }));
    }

    private static boolean hasEquilibriumPoint(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            if ((i == 0 && prefixSum[arr.length - 1] - prefixSum[i] == 0)
                    || (i == arr.length - 1 && prefixSum[i - 1] == 0)) {
                return true;
            } else {
                boolean isEquilibriumPoint = (i == 0 ? 0 : prefixSum[i - 1]) == (i == arr.length - 1 ? 0
                        : prefixSum[arr.length - 1] - prefixSum[i]);
                if (isEquilibriumPoint) {
                    return isEquilibriumPoint;
                }
            }
        }
        return false;
    }

    private static boolean naive(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int prefixSum = 0;
            int suffixSum = 0;
            for (int j = 0; j < i; j++) {
                prefixSum += arr[j];
            }
            for (int j = i + 1; j < arr.length; j++) {
                suffixSum += arr[j];
            }

            if (prefixSum == suffixSum) {
                return true;
            }
        }

        return false;
    }

    private static boolean naiveUsingONAuxSpace(int[] arr) {
        int[] prefixSum = new int[arr.length];
        int[] suffixSum = new int[arr.length];

        prefixSum[0] = arr[0];
        suffixSum[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            if (prefixSum[i] == suffixSum[i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean efficientUsingONTimeComplexityO1AuxSpace(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int lSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (lSum == sum - arr[i]) {
                return true;
            }
            lSum += arr[i];
            sum -= arr[i];
        }
        return false;
    }
}
