package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class PosOfRightMostDiffBit {
    public static void main(String[] args) {
        System.out.println(posOfRightMostDiffBit(52, 4));
    }
    public static int posOfRightMostDiffBit(int m, int n)
    {
        if(m == n){
            return -1;
        }
        int xor = m^n;
        int rightmostDiffBitNumber = xor & (~(xor -1));
        return (int)(Math.floor(Math.log10(rightmostDiffBitNumber)/Math.log10(2)) + 1);
    }
}
