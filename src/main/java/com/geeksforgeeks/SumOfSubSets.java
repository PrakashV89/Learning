package com.geeksforgeeks;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SumOfSubSets {
    public static void main(String[] args) {
        int[] inputArr = { 10, 5, 2, 3, 6 };
        int inputSum = 8;
        /*
         * System.out.println(get(inputArr, 0, inputSum)); System.out.println(get(new
         * int[] { 1, 2, 3 }, 0, 4)); System.out.println(get(new int[] { 10, 20, 15 },
         * 0, 37)); System.out.println(get(new int[] { 10, 20, 15 }, 0, 0));
         */
        print(new int[] { 10, 15, 20 }, 3, "{}");
        System.out.println(recursive(new int[] { 10, 5, 2, 3, 6 }, 5, 8));
    }

    static int get(int[] inputArr, int a, int inputSum) {
        if (inputSum == 0) {
            return 1;
        }

        int n = (a == inputSum) ? 1 : 0;
        if (a == inputArr.length - 1) {
            return n;
        }

        int sum = 0;
        for (int i = a + 1; i < inputArr.length; i++) {
            n += (inputArr[a] + inputArr[i] == inputSum) ? 1 : 0;
            sum += n;
            if (sum == inputSum) {
                ++n;
            }
        }

        return n + get(inputArr, ++a, inputSum);
    }

    static void print(int[] arr, int n, String a) {
        if (n == 0) {
            System.out.println(a);
            return;
        }

        print(arr, n - 1, a);
        print(arr, n - 1, a + ", " + arr[n - 1]);
    }

    static int recursive(int[] arr, int n, int sum) {
        if (n == 0) {
            return (sum == 0) ? 1 : 0;
        }

        return recursive(arr, n - 1, sum) + recursive(arr, n - 1, sum - arr[n - 1]);

    }
}
