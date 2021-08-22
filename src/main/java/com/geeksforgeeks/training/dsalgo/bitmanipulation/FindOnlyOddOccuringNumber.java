package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class FindOnlyOddOccuringNumber {
    public static void main(String[] args) {
        System.out.println(get(new int[]{5, 5, 4, 4, 3}));
        System.out.println(get(new int[]{8, 7, 7, 8, 30}));
    }

    //x ^ x = 0
    //x^y = y^x
    //x ^ 0 = x
    //x ^(y^z) = (x^y) ^z
    private static int get(int[] is) {
        int num = 0;
        for (int i = 0; i < is.length; i++) {
            num ^= is[i];
        }
        return num;
    }

    //Supports till 30
    private static long getNaive(int[] is) {
        long num = 0;
        for (var i = 0; i < is.length; i++) {
            num ^= 1 << is[i];
        }
        
        return (long) (Math.log10(num) / Math.log10(2));
    }

}
