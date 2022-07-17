package com.hackerrank.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class MinMax {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 4,3,10};
        int k =3;

        System.out.print(minmax(arr, k));
    }

    private static int minmax(int[] arr, int k) {

        int window = 0;
        int max =Integer.MIN_VALUE;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length - k; i++) {
            window++;
            queue.add(arr[i]);
            if(window == k){
                window = 0;
                queue.remove();
                max = Math.max(max, queue.stream().mapToInt(Integer::valueOf).min().getAsInt());

            }
        }

        return max;
    }
}
