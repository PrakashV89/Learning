package com.geeksforgeeks.training.dsalgo.bitmanipulation;

class CountSetBits{
    private static int[] tableLookup = new int[256]; 
    public static void main(String[] args) {
        for(int i=1; i< 256; i++){
            tableLookup[i] = i&1 + tableLookup[i/2]; 
        }

        System.out.println(getSetBitsUsingTableLookupMethod(Integer.MAX_VALUE));
    }

    private static int getSetBitsInJava(int n) {
       return (int) Math.floor(getLogBToX(2, n)) + 1;
    }

    private static double getLogBToX(int b, int x) {
        return Math.log(x)/Math.log(b);
    }

    private static int getSetBitsUsingDivide(int n){
        int countOfBits = 0;
        while(n > 0){
            countOfBits += (n&1);
            n = n/2;
        }
        return countOfBits;
    }

    private static int getSetBitsUsingBrianKerningamsAlgorithm(int n){
        int countOfBits = 0;
        while(n > 0){
            n = n & n-1;
            countOfBits++;
        }
        return countOfBits;
    }

    private static int getSetBitsUsingTableLookupMethod(int n){
        int countOfBits = 0;

        countOfBits += tableLookup[n & 0xff];
        n >>= 8;

        countOfBits += tableLookup[n & 0xff];
        n >>= 8;

        countOfBits += tableLookup[n & 0xff];
        n >>= 8;

        countOfBits += tableLookup[n & 0xff];

        return countOfBits;
    }
}