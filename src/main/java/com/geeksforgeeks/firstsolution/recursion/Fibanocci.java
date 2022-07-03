package com.geeksforgeeks.firstsolution.recursion;

public class Fibanocci {
    public static void main(String[] args) {
        System.out.println(fib(4));
    }

    private static int fib(int n) {
        if(n <= 1){
            return n;
        }
        return fib(n-2) + fib(n-1);
    }

    private static int tailRecursiveFib(int n) {
        if(n <= 1){
            return n;
        }
        return fib(n-2) + fib(n-1);
    }
}
