package com.geeksforgeeks.array.frequencyorcounting;

import java.util.HashMap;
import java.util.Map;

public class OccurringFrequency {
    public static void main(String[] args) {
        naiveSolution(new int[]{1, 1, 2});
        naiveSolution(new int[]{1, 2, 1});
        naiveSolution(new int[]{1, 2, 3});

        efficientSolution(new int[]{1, 1, 2});
        efficientSolution(new int[]{1, 2, 1});
        efficientSolution(new int[]{1, 2, 3});
    }

    private static void naiveSolution(int[] arr) {
        main:
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[j] == arr[i]){
                    continue main;
                }
            }
            int count = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] == arr[j]){
                    ++count;
                }
            }

            System.out.print(arr[i] + " : " + count + ", ");
        }

        System.out.println();
    }

    private static void efficientSolution(int[] arr){
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            frequencyMap.compute(arr[i], (k, v) -> v == null ? 1 : ++v);
        }

        frequencyMap.forEach((k, v) -> System.out.print(k + " : " + v + ", "));
        System.out.println();
    }
}
