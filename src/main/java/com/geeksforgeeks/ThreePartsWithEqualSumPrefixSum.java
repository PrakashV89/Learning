package com.geeksforgeeks;

public class ThreePartsWithEqualSumPrefixSum {
    public static void main(String[] args) {
        int[] arr = new int[] { 6, 2, 8, 4, 4 };
        System.out.println(is(arr));

    }

    private static boolean is(int[] arr) {

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int secondPartSum = 0;
            int thirdPartSum = 0;
            sum += arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (sum != secondPartSum) {
                    secondPartSum += arr[j];
                    continue;
                }

                if (sum == secondPartSum) {
                    thirdPartSum += arr[j];
                    if (thirdPartSum == sum && j == arr.length - 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
