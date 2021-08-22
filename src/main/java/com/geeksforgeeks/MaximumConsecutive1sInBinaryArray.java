package com.geeksforgeeks;

public class MaximumConsecutive1sInBinaryArray {
    public static void main(String[] args) {
        System.out.println(getUsingIterativeGFG_TC_Theta_NSquared(new int[] { 1, 0, 1, 1, 0, 1 }));
        System.out.println(getUsingIterativeGFG_TC_Theta_NSquared(new int[] { 0, 1, 1, 1, 0, 1, 1 }));
    }

    private static int get(int[] arr) {
        int consecutive1s = 0;
        int maxConsecutive1s = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                consecutive1s++;
            } else {
                maxConsecutive1s = Math.max(maxConsecutive1s, consecutive1s);
                consecutive1s = 0;
            }
        }
        maxConsecutive1s = Math.max(maxConsecutive1s, consecutive1s);

        return maxConsecutive1s;
    }

    private static int getUsingIterativeGFG_TC_Theta_NSquared(int[] arr) {
        int consecutive1s = 0;

        for (int i = 0; i < arr.length; i++) {
            int curr = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == 1) {
                    curr++;
                } else {
                    break;
                }
            }
            consecutive1s = Math.max(consecutive1s, curr);
        }

        return consecutive1s;
    }
}
