package com.geeksforgeeks;

public class MaximumCircularSubArraySum {
    public static void main(String[] args) {
        int[] arr = new int[] { 10, 5, -5 };
        System.out.println(getCircularSubArraySumItrGFG(arr));
        System.out.println(getCircularSubArraySumItrGFG(new int[] { 5, -2, 3, 4 }));
        System.out.println(getCircularSubArraySumItrGFG(new int[] { 2, 3, -4 }));
        System.out.println(getCircularSubArraySumItrGFG(new int[] { 8, -4, 3, -5, 4 }));
        System.out.println(getCircularSubArraySumItrGFG(new int[] { -3, 4, 6, -2 }));
        System.out.println(getCircularSubArraySumItrGFG(new int[] { -8, 7, 6 }));
        System.out.println(getCircularSubArraySumItrGFG(new int[] { 3, -4, 5, 6, -8, 7 }));
    }

    private static int getCircularSubArraySumItrKadens(int[] arr) {
        int maxSum = getNormalSum(arr);

        if (maxSum < 0) {
            return maxSum;
        }

        int arrSum = 0;
        for (int i = 0; i < arr.length; i++) {
            arrSum += arr[i];
            arr[i] = -arr[i];
        }

        int maxCircularSum = arrSum + getNormalSum(arr);
        return Math.max(maxSum, maxCircularSum);

    }

    private static int getNormalSum(int[] arr) {
        int res = arr[0], max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max + arr[i]);
            res = Math.max(res, max);
        }

        return res;
    }

    private static int getCircularSubArraySumItrGFG(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            int sums = sum;
            for (int j = 1; j < arr.length; j++) {
                sums += arr[(i + j) % arr.length];
                sum = Math.max(sum, sums);

            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    private static int getCircularSubArraySumItr(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            int sums = sum;
            int length = 1;
            for (int j = i + 1; j % arr.length <= j && j < 2 * arr.length && length < arr.length; j++, ++length) {
                sums += arr[j % arr.length];
                sum = Math.max(sum, sums);

            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    private static int getCircularSubArraySum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = Math.max(sum, getCircularSubArraySum(arr, i + 1, arr[i], 1, arr[i] + ""));
        }
        return sum;
    }

    private static int getCircularSubArraySum(int[] arr, int index, int sum, int length, String nodes) {
        if (length == arr.length) {
            System.out.println(nodes);
            return sum;
        }

        System.out.println(nodes);

        return Math.max(sum, getCircularSubArraySum(arr, index + 1, sum + arr[index % arr.length], length + 1,
                nodes + "->" + arr[index % arr.length]));
    }
}
