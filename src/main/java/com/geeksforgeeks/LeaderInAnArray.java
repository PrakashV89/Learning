package com.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LeaderInAnArray {
    public static void main(String[] args) {
        printLeadersUsingTimeComplexityO1AndSpaceComplexityO_NAndList(new int[] { 7, 10, 4, 3, 6, 5, 2 });
        printLeadersUsingTimeComplexityO1AndSpaceComplexityO_NAndList(new int[] { 7, 10, 4, 10, 6, 5, 2 });
        printLeadersUsingTimeComplexityO1AndSpaceComplexityO_NAndList(new int[] { 10, 20, 30 });
        printLeadersUsingTimeComplexityO1AndSpaceComplexityO_NAndList(new int[] { 30 });
    }

    private static void printLeaders(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] <= arr[j]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    private static void printLeadersUsingTimeComplexityO1(int[] arr) {
        int max = arr[arr.length - 1];
        System.out.print(max + " ");
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > max) {
                max = arr[i];
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    private static void printLeadersUsingTimeComplexityO1AndSpaceComplexityO_N(int[] arr) {
        int max = arr[arr.length - 1];
        int[] tmp = new int[arr.length];
        int j = 0;
        tmp[j++] = max;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > max) {
                max = arr[i];
                tmp[j++] = arr[i];
            }
        }
        System.out.println(Arrays.toString(reverseArr(tmp)));
    }

    private static int[] reverseArr(int[] tmp) {
        int i = 0;
        int j = tmp.length - 1;

        while (i < j) {
            int temp = tmp[j];
            tmp[j] = tmp[i];
            tmp[i] = temp;

            ++i;
            --j;
        }
        return tmp;
    }

    private static void printLeadersUsingTimeComplexityO1AndSpaceComplexityO_NAndList(int[] arr) {
        int max = arr[arr.length - 1];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(max);
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > max) {
                max = arr[i];
                list.add(arr[i]);
            }
        }
        Collections.reverse(list);
        list.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
