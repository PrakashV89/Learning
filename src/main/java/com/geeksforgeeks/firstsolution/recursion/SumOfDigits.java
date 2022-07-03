package com.geeksforgeeks.firstsolution.recursion;

public class SumOfDigits {
    public static void main(String[] args) {
        System.out.println(getSumOfN(1001));
    }

    private static int getSumOfN(int n) {
        return getSumOfN(n, 0);
    }

    private static int getSumOfN(int n, int sum) {
        if(n <= 9){
            return sum+n;
        }
        return getSumOfN(n/10, sum + n%10);
    }
}
