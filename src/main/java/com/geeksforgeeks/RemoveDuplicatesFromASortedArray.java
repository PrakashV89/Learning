package com.geeksforgeeks;

import static java.lang.System.out;

import java.util.Arrays;

public class RemoveDuplicatesFromASortedArray {
    public static void main(String[] args) {
        out.println(performUsingGFG(new int[] { 10, 20, 20, 30, 30, 40 }));
        out.println(performUsingGFG(new int[] { 10, 20, 30 }));
        out.println(performUsingGFG(new int[] { 10, 10, 10 }));
        out.println(performUsingGFG(new int[] { 10 }));
    }

    private static int perform1(int[] arr) {
        int size = arr.length;
        int i = 1;
        while (i < arr.length) {
            if (arr[i] == arr[i - 1]) {
                arr[i] = 0;
                --size;
            }
            ++i;
        }
        return size;
    }

    private static int perform(int[] arr) {
        int size = arr.length;
        int i = 0;
        while (i < arr.length - 1) {
            int j = i + 1;
            boolean resetI = false;

            if (arr[i] == arr[j] && arr[i] != 0) {
                while (j < arr.length) {
                    if (j + 1 < arr.length) {
                        arr[j] = arr[j + 1];
                        resetI = true;
                    } else {
                        arr[j] = 0;
                        break;
                    }
                    j++;
                }
                --size;
            }
            if (!resetI) {
                ++i;
            }
        }
        return size;
    }

    private static int performUsingTempArr(int[] arr) {
        int i = 0;
        int[] temp = new int[arr.length];
        int j = 0;
        temp[j++] = arr[0];
        while (i < arr.length) {
            if (i + 1 < arr.length && arr[i] != arr[i + 1]) {
                temp[j++] = arr[i + 1];
            }
            ++i;
        }
        for (int k = 0; k < j; k++) {
            arr[k] = temp[k];
        }
        return j;
    }

    private static int performUsingGFG(int[] arr) {
        int i = 1;
        int res = 1;
        while (i < arr.length) {
            if (arr[i] != arr[res - 1]) {
                arr[res] = arr[i];
                res++;
            }
            ++i;
        }
        return res;
    }
}
