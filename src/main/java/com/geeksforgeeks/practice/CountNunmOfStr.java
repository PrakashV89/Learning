package com.geeksforgeeks.practice;

class CountNumOfStr
{
    public static void main(String[] args) {
        System.out.println(countStr(3));
    }
    static long countStr(long n)
    {
        return 1+ (2*n) + ((n*(n-1)*(n-2))/2) + (n*(n-1)/2) + (n*(n-1));
    }
}