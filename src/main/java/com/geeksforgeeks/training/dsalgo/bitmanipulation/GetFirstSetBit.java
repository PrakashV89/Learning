package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class GetFirstSetBit {
    public static void main(String[] args) {
        System.out.println(getFirstSetBit(18));
        System.out.println(getFirstSetBit(0));
        System.out.println(getFirstSetBit(6636));
    }

    public static int getFirstSetBit(int n){
        if(n == 0){
            return 0;
        }
        n = n ^ (n & (n-1));
        return (int)(Math.floor(Math.log10(n)/Math.log10(2)) + 1);
    }
}
