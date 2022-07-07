package com.geeksforgeeks.array.frequencyorcounting;

import java.util.Arrays;

import static java.lang.Math.abs;

public class MissingOrRepeatingNumber {
    public static void main(String[] args) {
        final int[] arr = {2, 3, 2, 1, 5};
        sortAndReturn(arr);
        countAndReturn(arr);//hashing
        sumAndProductReturn(arr);
        negativeIndexAndReturn(arr);
    }

    private static void negativeIndexAndReturn(int[] arr) {
        int repeating = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[Math.abs(arr[i]) - 1];
            if(temp < 0){
                repeating = abs(arr[i]);
                continue;
            }
            arr[abs(arr[i])-1] = -arr[abs(arr[i]) - 1];
        }

        int missing = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > 0){
                missing = i+1;
            }
        }

        System.out.println(repeating + " " + missing);
    }

    private static void sumAndProductReturn(int[] arr) {
        int cs = 0;
        int es = 0;
        int cp = 1;
        int ep = 1;
        for (int i = 0; i < arr.length; i++) {
            cs += arr[i];
            es +=  i+1;

            cp *= arr[i];
            ep *= i+1;
        }

        int remaining = (es-cs)/((ep/cp)-1);
        int missing = ep*remaining/cp;

        System.out.println(remaining + " " + missing);

        /**
         * curr_sum = expected_sum -x + y
         * cs = es - x + y
         * x+cs = es + y
         * x= es +y -cs
         * x-es+cs = y
         *
         *
         * curr_product = expected_product * y/x
         * y-x = curr_sum-expected_sum
         * y/x = curr_product/expected_product
         *
         * x = y*ep/cp
         *
         * y*(ep/cp) -es + cs = y
         *
         * x = y/cpbyep
         * x-y = es - cs
         * y/cpbyep -y = escs
         * y((1/cpbyep) - 1) = escs
         * y = escs / ((1/cpbyep) - 1)
         *
         */
    }

    private static void countAndReturn(int[] arr) {
        int[] count = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - 1]++;
        }

        int remaining = -1;
        int missing = -1;
        for (int i = 0; i < count.length; i++) {
            if(count[i] > 1){
                remaining = i + 1;
            }
            else if(count[i] == 0){
                missing = i + 1;
            }
        }

        System.out.println(remaining + " " + missing);
    }

    private static void sortAndReturn(int[] arr) {
        Arrays.sort(arr);

        int repeating = -1;
        int missing = -1;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i-1] == arr[i]){
                repeating = arr[i];
                continue;
            }

            if(arr[i] - arr[i-1] > 1){
                missing = arr[i-1]+1;
            }
        }

        System.out.println(repeating + " " + missing);
    }
}
