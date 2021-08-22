package com.geeksforgeeks;

public class RecursionPrint1ToN {
    public static void main(String[] args) {
        print1ToN(9);
    }

    private static void print1ToN(int n) {
        if(n == 0){
            return;
        }

        print1ToN(n-1);
        System.out.println(n);
    }
}
