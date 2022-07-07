package com.geeksforgeeks.array.subarray;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithEqualNumberOf0sAnd1s {
    public static void main(String[] args) {
        System.out.println(naiveSolution(new int[]{1, 0, 1, 1, 1, 0, 0}));
        System.out.println(naiveSolution(new int[]{1, 0, 1, 1, 1}));
        System.out.println(naiveSolution(new int[]{1, 1, 1, 1}));

        System.out.println(efficientSolution(new int[]{1, 0, 1, 1, 1, 0, 0}));
        System.out.println(efficientSolution(new int[]{1, 0, 1, 1, 1}));
        System.out.println(efficientSolution(new int[]{1, 1, 1, 1}));
    }

    private static int naiveSolution(int[] arr) {
        int longestSubArrayWithEqualNumberOf0sAnd1s = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                if(arr[j] == 0){
                    sum -= 1;
                }
                else{
                    sum += 1;
                }

                if(sum == 0){
                    longestSubArrayWithEqualNumberOf0sAnd1s = Math.max(longestSubArrayWithEqualNumberOf0sAnd1s, j-i + 1);
                }
            }
        }

        return longestSubArrayWithEqualNumberOf0sAnd1s;
    }


    private static int efficientSolution(int[] arr){
        int lsArr = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int prefixSum = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if(arr[i] == 0){
                prefixSum -= 1;
            }
            else{
                prefixSum += 1;
            }

            if(prefixSum == 0){
                lsArr = i+1;
            }

            int finalI = i;
            prefixMap.putIfAbsent(prefixSum, finalI);

            if(prefixMap.containsKey(prefixSum)){
                lsArr = Math.max(lsArr, i-prefixMap.get(prefixSum));
            }
        }

        return lsArr;
    }
}
