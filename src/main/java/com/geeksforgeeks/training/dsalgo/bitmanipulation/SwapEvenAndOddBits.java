package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class SwapEvenAndOddBits {
    public static void main(String[] args) {
        System.out.println(swapBits(2));
    }

    //Function to swap odd and even bits.
    public static int swapBits(int n) 
    {
	    int evenBits = n & 0xAAAAAAAA;
	    int oddBits = n & 0x55555555;
	    
	    return (evenBits >> 1) | (oddBits << 1);
	}
    
}
