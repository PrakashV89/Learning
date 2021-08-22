package com.geeksforgeeks;

public class PrintAllPermutations {
    public static void main(String[] args) {
        printUsingGFGSolution(new StringBuilder("ABCD"), 0);
        printUsingGFGSolution(new StringBuilder("ABC"), 0);
        printUsingGFGSolution(new StringBuilder("AB"), 0);
        printUsingGFGSolution(new StringBuilder(""), 0);
    }

    static void print(String str) {
        if (str.isEmpty() || str.length() == 1) {
            System.out.println(str);
            return;
        }
        print(str, 0);
        if (str.length() > 2) {
            print(new StringBuffer(str).reverse().toString(), 0);
        }

    }

    static void print(String str, int n) {
        if (n == str.length()) {
            return;
        }
        System.out.println(str);
        print(str.charAt(str.length() - 1) + str.substring(0, str.length() - 1), ++n);

    }

    static void printUsingGFGSolution(StringBuilder str, int index) {
        if (index == str.length() - 1) {
            System.out.println(str);
            return;
        }

        for (int i = index; i < str.length(); i++) {
            swap(str, index, i);
            printUsingGFGSolution(str, index + 1);
            swap(str, index, i);
        }
    }

    private static void swap(StringBuilder str, int index, int i) {
        char tmp = str.charAt(index);
        str.setCharAt(index, str.charAt(i));
        str.setCharAt(i, tmp);
    }
}
