package com.geeksforgeeks;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MajorityElement {
    public static void main(String[] args) {
        int arr[] = { 8, 3, 4, 8, 8 };

        System.out.println(get2(arr));
        System.out.println(get1(arr));
        System.out.println(get1(new int[] { 3, 7, 4, 7, 7, 5 }));
    }

    private static int get2(int[] arr) {
        int res = 0, counter = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[res]) {
                counter++;
            } else {
                counter--;
            }

            if (counter == 0) {
                res = i;
                counter = 1;
            }
        }

        counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[res] == arr[i]) {
                counter++;
            }
        }

        if (counter <= arr.length / 2) {
            res = -1;
        }
        return res;
    }

    private static int get1(int[] arr) {
        /*
         * return
         * Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(),
         * Collectors.counting()));
         */

        for (int i = 0; i < arr.length; i++) {
            int count = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }

            if (count > arr.length / 2) {
                return i;
            }
        }

        return -1;
    }

    private static int get(int[] arr) {
        /*
         * return
         * Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(),
         * Collectors.counting()));
         */

        Optional<Map.Entry<Integer, Long>> val = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).filter(e -> e.getValue() > arr.length / 2);
        return val.isPresent() ? val.get().getKey() : -1;
    }
}
