package com.geeksforgeeks;

import java.util.Arrays;

public class LeftRotateArrayByOne {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(leftRotateArrayByOne(new int[] { 1, 2, 3, 4, 5 })));
    }

    private static int[] leftRotateArrayByOne(int[] arr) {
        int tmp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = tmp;
        return arr;
    }

    private static int[] leftRotateArrayByOneUsingTmpArray(int[] arr) {
        int[] tmp = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            tmp[i - 1] = arr[i];
        }
        tmp[arr.length - 1] = arr[0];
        return tmp;
    }
}
