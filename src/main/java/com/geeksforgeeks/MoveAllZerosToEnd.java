package com.geeksforgeeks;

import java.util.Arrays;

public class MoveAllZerosToEnd {
    public static void main(String[] args) {
        int[] arr = { 8, 5, 0, 10, 0, 20 };
        System.out.println(Arrays.toString(performUsingTimeComplexityONSquared(arr)));
    }

    private static int[] perform(int[] arr) {
        int[] tmp = new int[arr.length];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                tmp[j++] = arr[i];
            }
        }

        return tmp;
    }

    private static int[] performUsingO1AuxSpace(int[] arr) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i);
            if (arr[i] != 0) {
                System.out.println(arr[i] + "!=0" + ":" + count);
                arr[count] = arr[i];
                if (count != i) {
                    arr[i] = 0;
                }
                ++count;
            }

        }

        return arr;
    }

    private static int[] performUsingTimeComplexityONSquared(int[] arr) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != 0) {
                        int tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                        break;
                    }
                }
            }

        }

        return arr;
    }
}
