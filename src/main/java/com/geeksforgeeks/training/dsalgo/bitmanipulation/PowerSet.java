package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class PowerSet {
    public static void main(String[] args) {
        printPowerSetOfStr("abc");
    }

    private static void printPowerSetOfStr(String str) {
        char[] strArr = str.toCharArray();
        int n = str.length();
        int powSize = (int)Math.pow(2, n);
        for(int i=0; i< powSize; i++){
            for(int j=0;j< n; j++){
                if((i & (1<<j)) != 0){
                    System.out.print(strArr[j]);
                }
            }
            System.out.println();
        }
    }
}
