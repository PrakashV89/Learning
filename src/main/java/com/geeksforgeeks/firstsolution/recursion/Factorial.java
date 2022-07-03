package com.geeksforgeeks.firstsolution.recursion;

public class Factorial {
    public static void main(String[] args) {
        System.out.println(fact(4));
    }

    private static int fact(int n){

        return fact(n, 1);
    }

    private static int fact(int n, int fact) {
        if(n == 0 || n == 1){
            return fact;
        }

        return fact(n-1, n*fact);
    }
}
