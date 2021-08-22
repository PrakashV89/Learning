package com.training;

import java.util.ArrayList;
import java.util.List;

public class GlobalMaximum {
    public static void main(String[] args) {
        System.out.println(globalMaximum(new int[] { 2, 3, 5, 9 }, 3));
    }

    private static int globalMaximum(int[] arr, int m) {
        return subSequence(arr, m, 0, "", new ArrayList<>());
    }

    private static int subSequence(int[] arr, int m, int i, String tabs, List<Integer> list) {
        // System.out.println(tabs + len + ":" + i + ":" + (m - len) + ":" + (arr.length
        // - i));

        if (list != null && list.size() == m) {
            return getMinDiff(list);
        }
        int max = Integer.MIN_VALUE;
        for (int j = i; j < arr.length; ++j) {
            List<Integer> li = new ArrayList<>(list);
            li.add(arr[j]);
            max = Math.max(max, subSequence(arr, m, j + 1, tabs + "\t", li));

        }

        return max;
    }

    private static int getMinDiff(List<Integer> list) {
        Integer secondMinVal = Integer.MAX_VALUE;
        Integer minVal = Integer.MAX_VALUE;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < minVal) {
                secondMinVal = minVal;
                minVal = list.get(i);
            } else if (list.get(i) < secondMinVal && list.get(i) > minVal) {
                secondMinVal = list.get(i);
            }

        }
        return secondMinVal - minVal;
    }

    private static int getSize(List<Integer> list) {
        return list == null ? 0 : list.size();
    }
}