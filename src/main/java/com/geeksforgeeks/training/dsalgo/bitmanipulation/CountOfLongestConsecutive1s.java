package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class CountOfLongestConsecutive1s {
    public static void main(String[] args) {
        System.out.print(maxConsecutiveOnes(201));
    }
    public static int maxConsecutiveOnes(int N) {
        int count = 0;
        while(N>0){
            N = N&(N<<1);
            count++;
        }
        return count;
    }
    
    public static int maxConsecutiveOnesMyWay(int n) {
        int bits = (int)Math.floor(log2(n)) + 1;
        int length = 0;
        int maxLength = 0;
        for(int i = 0; i< bits; i++){
            int bit = (n>>i) & 1;
            if(bit == 0 ){
                if(length != 0){
                    maxLength = Math.max(length, maxLength);
                    length = 0;
                }
            }
            else if(bit == 1){
                length++;
                if(i == bits -1){
                    maxLength = Math.max(length, maxLength);
                    length = 0;
                }
            }
        }
    
        return maxLength;
    }
    
    static int log2(int n){
        return (int)(Math.log10(n)/Math.log10(2));
    }
}
