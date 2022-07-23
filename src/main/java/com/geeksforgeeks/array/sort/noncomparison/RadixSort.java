package com.geeksforgeeks.array.sort.noncomparison;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class RadixSort {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(sortUsingCountSort(new int[]{10, 20, 400, 802, 1, 2, 9, 10})));
        System.out.println(Arrays.toString(sortUsingBucketSort(new int[]{10, 20, 400, 802, 1, 2, 9, 10})));
        System.out.println(Arrays.toString(sortStringUsingCountSort(new String[]{"Apple", "Bride", "Astro", "Sheep"})));
        System.out.println(Arrays.toString(sortStringUsingBucketSort(new String[]{"Apple", "Bride", "Astro", "Sheep"})));
        final String[] arr = {"Apple", "Bride", "Astro", "Sheep"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static String[] sortStringUsingBucketSort(String[] arr) {
        if(arr.length <= 1){
            return arr;
        }

        int maxLength = arr[0].length();

        for (int i = 0; i < maxLength; i++) {
            LinkedList<String>[] bucket = new LinkedList['z' - 'a' + 1];

            for (int j = 0; j < bucket.length; j++) {
                bucket[j] = new LinkedList<>();
            }

            for (int j = 0; j < arr.length; j++) {
                int idx = (Character.toLowerCase(arr[j].charAt(arr[j].length() - 1 - i)) - 'a');
                bucket[idx].add(arr[j]);
            }

            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                while(!bucket[j].isEmpty()){
                    arr[index++] = bucket[j].remove();
                }
            }

//            System.out.println(Arrays.toString(arr));
        }

        return arr;
    }

    private static String[] sortStringUsingCountSort(String[] arr) {
        int maxLength = arr[0].length();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() > maxLength) {
                maxLength = arr[i].length();
            }
        }


        for (int i = 0; i < maxLength; i++) {
            int[] count = new int['z' - 'a' + 1];
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].length() - 1 - i >= 0) {
                    count[Character.toLowerCase(arr[j].charAt(arr[j].length() - 1 - i)) - 'a']++;
                } else {
                    count[0]++;
                }
            }

            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }

            String[] output = new String[arr.length];
            for (int j = output.length - 1; j >= 0; j--) {
                if (arr[j].length() - 1 - i >= 0) {
                    output[count[Character.toLowerCase(arr[j].charAt(arr[j].length() - 1 - i)) - 'a'] - 1] = arr[j];
                    count[Character.toLowerCase(arr[j].charAt(arr[j].length() - 1 - i)) - 'a']--;
                } else {
                    output[count[0] - 1] = arr[j];
                    count[0]--;
                }
            }

            for (int j = 0; j < arr.length; j++) {
                arr[j] = output[j];
            }
        }
        return arr;
    }

    private static int[] sortUsingBucketSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        int digits = 0;
        int i = 0;
        while (maxVal > Math.pow(10, i)) {
            i++;
            digits++;
        }


        for (int digit = 0; digit < digits; digit++) {
            LinkedList<Integer>[] bucket = new LinkedList[10];
            for (int j = 0; j < bucket.length; j++) {
                bucket[j] = new LinkedList<>();
            }

            for (int j = 0; j < arr.length; j++) {
                final int currDigit = (int) ((arr[j] / Math.pow(10, digit)) % 10);
                bucket[currDigit].add(arr[j]);
            }

            for (int j = 0; j < arr.length; j++) {
                Collections.sort(bucket[j]);

            }
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                while (bucket[j].size() > 0) {
                    arr[index++] = bucket[j].remove();
                }
            }
        }

        return arr;
    }

    private static int[] sortUsingCountSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int minVal = arr[0];
        int maxVal = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            }

            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        int digits = (int) Math.ceil(Math.log10(maxVal));
        int range = maxVal - minVal + 1;

//        System.out.println(digits);

        for (int i = 1; i <= digits; i++) {
            int[] count = new int[10];
            int[] output = new int[arr.length];
            int[] digitArr = new int[arr.length];
            for (int j = 0; j < arr.length; j++) {
                int digitTimes = i;
                int val = arr[j];
                int digit = 0;
                while (digitTimes > 0) {
                    if (val == 0) {
                        digit = 0;
                        break;
                    }
                    digit = val % 10;
                    val = val / 10;

                    digitTimes--;
                }
                digitArr[j] = digit;
//                System.out.printf("Digit : %d, Val : %d, Digit For : %d\n", i, arr[j], digit);
                count[digit]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }

            for (int j = arr.length - 1; j >= 0; j--) {
                output[count[digitArr[j]] - 1] = arr[j];

                count[digitArr[j]]--;
            }

            for (int j = 0; j < arr.length; j++) {
                arr[j] = output[j];
            }
//            System.out.printf("Digit : %d, Arr: %s\n", i, Arrays.toString(arr));
        }

        return arr;
    }
}
