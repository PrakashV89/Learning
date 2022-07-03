package com.geeksforgeeks.firstsolution.recursion;

public class StringPermutations {
    public static void main(String[] args) {
        generate("ABC");
    }

    private static void generate(String s) {
        generate(s.toCharArray(), 0, s.length() - 1);
    }

    private static void generate(char[] arr, int l, int r) {
        if( l == r){
            System.out.println(new String(arr));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            swap(arr, l, i);
            generate(arr, l+1, r);
            swap(arr, l, i);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp= arr[i];

        arr[i] = arr[j];
        arr[j] = temp;
    }
}
