package com.geeksforgeeks;

public class SubArrayOfGivenSumWindowSlidingTechnique {
    public static void main(String[] args) {
        System.out.println((boolean) printUsingGFG(new int[] { 1, 4, 20, 3, 10, 5 }, 33));
        System.out.println((boolean) printUsingGFG(new int[] { 4, 7, -3, 1, 2 }, 9));
    }

    private static boolean printUsingGFG(int[] arr, final int sum) {
        int subArraySum = arr[0];
        int start = 0;
        int numberOfOps = 1;
        for (int end = 1; end < arr.length; end++) {
            while (subArraySum > sum && start < end - 1) {
                subArraySum -= arr[start];
                ++start;
            }
            if (subArraySum == sum) {
                return true;
            }
            if (end < arr.length) {
                subArraySum += arr[end];
            }

        }
        return subArraySum == sum;
    }

    private static boolean print(int[] arr, final int sum) {
        int subArraySum = arr[0];
        int start = 0;
        for (int i = 1; i < arr.length; i++) {
            subArraySum += arr[i];
            if (subArraySum == sum) {
                return true;
            }
            if (subArraySum > sum) {
                while (subArraySum > sum && start < i) {
                    subArraySum -= arr[start];
                    if (subArraySum == sum) {
                        return true;
                    }
                    ++start;
                }
            }
        }
        return false;
    }

    private static boolean print1(int[] arr, int sum) {
        for (int i = 0; i < arr.length; i++) {
            int sumSubArray = 0;
            for (int j = i; j < arr.length; j++) {
                sumSubArray += arr[j];
                if (sumSubArray == sum) {
                    return true;
                }
            }
        }
        return false;
    }
}
