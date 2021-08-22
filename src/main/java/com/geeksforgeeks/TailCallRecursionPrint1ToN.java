package com.geeksforgeeks;

public class TailCallRecursionPrint1ToN {
    public static void main(String[] args) {
        print1ToN(9, 1);
    }

    private static void print1ToN(int n, int k) {
        if (n == 0) {
            return;
        }
        System.out.print(k);
        print1ToN(n - 1, k + 1);
    }
}
