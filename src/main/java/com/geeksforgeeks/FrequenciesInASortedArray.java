package com.geeksforgeeks;

public class FrequenciesInASortedArray {
    public static void main(String[] args) {
        printUsingGFG(new int[] { 10, 10, 10, 25, 30, 30 });
        printUsingGFG(new int[] { 10, 10, 10, 30 });
        printUsingGFG(new int[] { 10 });
    }

    private static void print(int[] arr) {
        int currVal = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == currVal) {
                count++;
            } else {
                System.out.println(currVal + " " + count);
                currVal = arr[i];
                count = 1;
            }
        }
        System.out.println(currVal + " " + count);
    }

    private static void printUsingGFG(int[] arr) {
        int i = 1;
        int freq = 1;
        while (i < arr.length) {

            while (i < arr.length && arr[i] == arr[i - 1]) {
                ++freq;
                ++i;
            }
            System.out.println(arr[i - 1] + " " + freq);
            freq = 1;
            ++i;
        }

        if (arr.length == 1 || arr[arr.length - 1] != arr[arr.length - 2]) {
            System.out.println(arr[arr.length - 1] + " " + freq);
        }
    }
}
