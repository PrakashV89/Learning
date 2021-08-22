package com.geeksforgeeks;

public class TailCallRecursionFactorial {
    public static void main(String[] args) {
        System.out.println(vanillaRecursionFact(10));
        System.out.println(tailCallOptimizedRecursionFact(10, 1));
    }

    private static int tailCallOptimizedRecursionFact(int n, int k) {
        if (n == 0) {
            return k;
        }
        return tailCallOptimizedRecursionFact(n - 1, k * n);
    }

    private static int vanillaRecursionFact(int n) {
        if (n == 0) {
            return 1;
        }
        return n * vanillaRecursionFact(n - 1);
    }
}
