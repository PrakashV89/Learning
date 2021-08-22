package com.geeksforgeeks.training.dsalgo.bitmanipulation;


 // } Driver Code Ends
//User function Template for C++

class CountOfBitsToBeFlipped{
    public static void main(String[] args) {
        System.out.println(countBitsFlip(20, 25));
    }
    // Function to find number of bits needed to be flipped to convert A to B
    static int countBitsFlip(int a, int b){
        int totalBitsToBeFlipped = 0;
        int c = a^b;
        while(c > 0){
            totalBitsToBeFlipped++;
            c= c & (c-1);
        }
        return totalBitsToBeFlipped;
    }

    
}// } Driver Code Ends