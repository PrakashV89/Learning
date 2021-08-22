package com.geeksforgeeks;

import java.util.Arrays;

public class RearrangeArrayAlternately {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        rearrange(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void rearrange(int[] arr) {
        int maxPosition = arr.length - 1;
        int minPosition = 0;
        int maxElement = arr[maxPosition] + 1;

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] += (arr[maxPosition--] % maxElement) * maxElement;
            } else {
                arr[i] += (arr[minPosition++] % maxElement) * maxElement;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] /= maxElement;
        }
    }
}
