package com.geeksforgeeks;

public class MaximumLengthEvenOddSubArray {
    public static void main(String[] args) {
        System.out.println(getIterativeON(new int[] { 10, 12, 14, 7, 8 }));
        System.out.println(getIterativeON(new int[] { 7, 10, 13, 14 }));
        System.out.println(getIterativeON(new int[] { 10, 12, 8, 4 }));
    }

    private static int get(int[] arr, int start, int length, boolean isOdd) {
        if (start == arr.length) {
            return length;
        }

        int maxLength = 0;
        for (int i = start; i < arr.length; i++) {
            if (start == 0) {
                isOdd = (arr[i] % 2 != 0);
                maxLength = Math.max(maxLength, get(arr, i + 1, length + 1, !isOdd));
            } else if ((arr[i] % 2 != 0) == isOdd) {
                maxLength = Math.max(maxLength, get(arr, i + 1, length + 1, !isOdd));
            }
        }
        return maxLength;
    }

    private static int getIterative(int[] arr) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int length = 1;
            boolean isOdd = (arr[i] % 2 != 0);
            for (int j = i + 1; j < arr.length; j++) {
                isOdd = !isOdd;
                if ((arr[j] % 2 != 0) == isOdd) {
                    ++length;
                } else {
                    break;
                }

            }

            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    private static int getIterativeON(int[] arr) {
        int length = 1;

        for (int i = 1; i < arr.length; i++) {
            if ((arr[i - 1] % 2 != 0 && arr[i] % 2 == 0) || (arr[i - 1] % 2 == 0 && arr[i] % 2 != 0)) {
                length++;
            } else {
                length = 1;
            }
        }

        return length;
    }
}
