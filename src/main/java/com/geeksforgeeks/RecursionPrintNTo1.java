package com.geeksforgeeks;

public class RecursionPrintNTo1 {
    public static void main(String[] args) {
        printNTill0(9);   
    }

    static void printNTill0(int n){
        if(n == 0){
            return;
        }
        System.out.println(n);
        printNTill0(--n);
    }
}
