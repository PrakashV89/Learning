package com.geeksforgeeks.array.subarray;

import java.util.HashSet;
import java.util.Set;

public class SubArrayWithSumZero {
    public static void main(String[] args) {

        System.out.println(naiveApproach(new int[]{5, -1, -2, -2, 4}));
        System.out.println(naiveApproach(new int[]{5, -1, -2, 0, 4}));
        System.out.println(naiveApproach(new int[]{5, -1, -2, -9, 3}));

        System.out.println(efficientApproach(new int[]{5, -1, -2, -2, 4}));
        System.out.println(efficientApproach(new int[]{5, -1, -2, 0, 4}));
        System.out.println(efficientApproach(new int[]{5, -1, -2, -9, 3}));
    }

    private static boolean naiveApproach(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int subArrSum = 0;
            for (int j = i; j < arr.length; j++) {
                subArrSum += arr[j];

                if(subArrSum == 0){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean efficientApproach(int[] arr){
        int prefixSum = 0;
        Set<Integer> prefixSumSet = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            if(prefixSumSet.contains(prefixSum)){
                return true;
            }
            if(prefixSum == 0){
                return true;
            }

            prefixSumSet.add(prefixSum);
        }
        return false;
    }
}
