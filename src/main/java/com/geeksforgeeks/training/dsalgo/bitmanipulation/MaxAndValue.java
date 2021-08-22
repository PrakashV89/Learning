package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class MaxAndValue {
    public static void main(String[] args) {
        System.out.println(maxAND(new int[]{4,8,12,6}, 4));
    }


    private static int maxAND(int[] arr, int N) {
        int maxAndValue = 0;
        for (int i = 31; i >= 0 ; i--) {
            int numberWithBitsMatching = 0;
            for(int j = 0;j < arr.length;j++){
                int res = (maxAndValue | 1<<i);
                if((res & arr[j]) == res){
                    numberWithBitsMatching ++;
                }
            }
            if(numberWithBitsMatching >= 2){
                maxAndValue |= 1 << i;
            }
        }
        return maxAndValue;
    }


    // Function for finding maximum AND value.
    public static int maxANDNaiveLogic (int[] arr, int N) {
        int maxAndValue = 0;
        for(int i = 0; i < N; i++){
            for(int j=i+1; j < N; j++){
                int andValue = arr[i] & arr[j];
                if(andValue > maxAndValue){
                    maxAndValue = andValue;
                }
            }
        }
        
        return maxAndValue;
        
    }
}
