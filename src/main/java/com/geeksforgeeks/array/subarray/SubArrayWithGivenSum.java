package com.geeksforgeeks.array.subarray;

import java.util.HashSet;
import java.util.Set;

public class SubArrayWithGivenSum {
    public static void main(String[] args) {
        System.out.println(naiveApproach(new int[]{1, 2, 3, 4, 1, -1}, 7));
        System.out.println(naiveApproach(new int[]{1, 2, 3, 4, 1, -1}, 15));

        System.out.println(betterApproachUsingHashing(new int[]{1, 2, 3, 4, 1, -1}, 7));
        System.out.println(betterApproachUsingHashing(new int[]{1, 2, 3, 4, 1, -1}, 15));

        System.out.println(bestApproachSlidingWindowTechnique(new int[]{1, 2, 3, 4, 1, -1}, 7));
        System.out.println(bestApproachSlidingWindowTechnique(new int[]{1, 2, 3, 4, 1, -1}, 15));
    }

    private static boolean naiveApproach(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];

                if(sum == k){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean betterApproachUsingHashing(int[] arr, int k){
        int prefixSum = 0;
        Set<Integer> prefixSumSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            if(prefixSum == k){
                return true;
            }

            if(prefixSumSet.contains(prefixSum -k)){
                return true;
            }

            prefixSumSet.add(arr[i]);
        }
        return false;
    }

    private static boolean bestApproachSlidingWindowTechnique(int[] arr, int k){
        int sum = 0;
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sum == k){
                return true;
            }

            if(sum > k){
                sum -= arr[start++];
                continue;
            }
        }

        return false;
    }
}
