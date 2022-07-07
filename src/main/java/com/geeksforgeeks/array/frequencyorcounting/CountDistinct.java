package com.geeksforgeeks.array.frequencyorcounting;

import java.util.HashSet;
import java.util.Set;

public class CountDistinct {
    public static void main(String[] args) {
        System.out.println(naiveSolution(new int[]{1, 1, 1}));
        System.out.println(naiveSolution(new int[]{1, 2, 1}));
        System.out.println(naiveSolution(new int[]{1, 2, 3}));

        System.out.println(efficientSolution(new int[]{1, 1, 1}));
        System.out.println(efficientSolution(new int[]{1, 2, 1}));
        System.out.println(efficientSolution(new int[]{1, 2, 3}));
    }

    private static int naiveSolution(int[] arr) {
        int distinctCount = 0;

        for (int i = 0; i < arr.length; i++) {
            boolean alreadyCounted = false;
            for (int j = 0; j < i; j++) {
                if(arr[i] == arr[j]){
                    alreadyCounted = true;
                }
            }

            if(alreadyCounted){
               continue;
            }

            distinctCount++;
        }

        return distinctCount;
    }

    private static int efficientSolution(int[] arr){
        Set<Integer> distinct = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            distinct.add(arr[i]);
        }

        return distinct.size();
    }
}
