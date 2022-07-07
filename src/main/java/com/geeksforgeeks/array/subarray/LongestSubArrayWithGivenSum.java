package com.geeksforgeeks.array.subarray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubArrayWithGivenSum {

    public static void main(String[] args) {
        System.out.println(naiveApproach(new int[]{5, 8, -4, -4, 9, -2, -2}, 0));
        System.out.println(naiveApproach(new int[]{3, 1, 0, 1, 8, 2, 3, 6}, 5));
        System.out.println(naiveApproach(new int[]{8, 3, 7}, 15));


        System.out.println(betterApproach(new int[]{5, 8, -4, -4, 9, -2, -2}, 0));
        System.out.println(betterApproach(new int[]{3, 1, 0, 1, 8, 2, 3, 6}, 5));
        System.out.println(betterApproach(new int[]{8, 3, 7}, 15));
    }

    private static int naiveApproach(int[] arr, int k) {
        int largestSubArray = 0;
        Set<Integer> prefixSumSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];

                if (sum == k) {
                    largestSubArray = Math.max(largestSubArray, j - i + 1);
                }
            }
        }
        return largestSubArray;
    }

    private static int betterApproach(int[] arr, int k){
        int largestSubArray = 0;
        Map<Integer, Integer> maxSizeByPrefixSum = new HashMap<>();
        int prefixSum = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            if(prefixSum == k){
                largestSubArray = i + 1;
            }

            if(!maxSizeByPrefixSum.containsKey(prefixSum)){
                maxSizeByPrefixSum.put(prefixSum, i);
            }

            if(maxSizeByPrefixSum.containsKey(prefixSum - k)){
                largestSubArray = Math.max(largestSubArray, i- maxSizeByPrefixSum.get(prefixSum-k));
            }
        }

        return largestSubArray;
    }
}
