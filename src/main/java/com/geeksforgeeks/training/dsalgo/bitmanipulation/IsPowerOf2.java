package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class IsPowerOf2 {
    public static void main(String[] args) {
        System.out.println(isPowerofTwo(16));
    }

    // Function to check if given number n is a power of two.
    public static boolean isPowerofTwo(long n){
        double powerOf2 = log2(n);
        return Math.floor(powerOf2) == Math.ceil(powerOf2);
        
    }
    
    private static double log2(long n){
        return Math.log10(n)/Math.log10(2);
    }
}
