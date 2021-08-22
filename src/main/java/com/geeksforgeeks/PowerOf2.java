package com.geeksforgeeks;

public class PowerOf2 {
    public static void main(String[] args) {
        System.out.println(isUsingBrianKerningamsAlgorithmOptimized(32));
        System.out.println(isUsingBrianKerningamsAlgorithmOptimized(48));
    }

    private static String is(int n) {
        if(n == 0){
            return "No";
        }
       while(n != 1){
           if(n%2 != 0){
               return "No";
           }
            n = n/2;
       }
       return "Yes";
    }

    private static String isUsingBrianKerningamsAlgorithm(int n) {
        if(n == 0){
            return "No";
        }
        int countOfBits = 0;
       while(n > 0){
           if(countOfBits > 0){
               return "No";
           }
           n= n & n-1;
           countOfBits++;
       }
       return "Yes";
    }

    private static String isUsingBrianKerningamsAlgorithmOptimized(int n) {
       return (n!= 0 && (n & (n-1)) == 0) ? "Yes" : "No";
    }
}
