package com.geeksforgeeks.firstsolution.recursion;

public class SumOfNNaturalNumbers {
    public static void main(String[] args) {
        System.out.println(sumOfN(4));
    }

    private static int sumOfN(int n){
        return sumOfN(n, 0);
    }

    private static int sumOfN(int n, int k) {
        if(n == 0){//Base Condition
            return k;
        }
        return sumOfN(n-1, n+k);
    }

    //Theta(1) solution - best to know math - n*(n+1)/2
    private static int sumOfNNaturalNumbers(int n, int k) {
        return n*(n+1)/2;
    }
}
