package com.geeksforgeeks;

public class MaxOccuringElementGiveNRangesPrefixSum {
    public static void main(String[] args) {
        int[] l = { 1, 2, 5, 15 };
        int[] r = { 5, 8, 7, 18 };

        getUsingGFG(l, r);
        getUsingGFG(new int[] { 1, 2, 3 }, new int[] { 3, 5, 7 });
        getUsingGFG(new int[] { 3, 3, 3 }, new int[] { 3, 3, 3 });

    }

    private static void get(int[] l, int[] r) {
        int lVal = l[0];
        int rVal = r[0];

        for (int i = 1; i < r.length; i++) {
            if (l[i] > lVal && l[i] <= rVal) {
                lVal = l[i];
            }

            if (r[i] < rVal && r[i] >= lVal) {
                rVal = r[i];
            }
        }

        System.out.println(Math.max(lVal, rVal));
    }

    private static void getUsingGFG(int[] l, int[] r) {
        int[] arr = new int[1000];
        for (int i = 0; i < l.length; i++) {
            arr[l[i]]++;
            arr[r[i] + 1]--;
        }
        int maxVal = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];

            if (maxVal < arr[i]) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }

        System.out.println(maxIndex);
    }
}
