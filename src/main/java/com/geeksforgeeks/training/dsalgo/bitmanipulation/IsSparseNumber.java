package com.geeksforgeeks.training.dsalgo.bitmanipulation;

/*
Given a number N. The task is to check whether it is sparse or not. A number is said to be a sparse number if no two or more consecutive bits are set in the binary representation.

Example 1:

Input: N = 2
Output: true
Explanation: Binary Representation of 2 is 10, 
which is not having consecutive set bits. 
So, it is sparse number.
Example 2:

Input: N = 3
Output: false
Explanation: Binary Representation of 3 is 11, 
which is having consecutive set bits in it. 
So, it is not a sparse number.
*/
public class IsSparseNumber {
    public static void main(String[] args) {
        System.out.println(isSparse(10922));
        System.out.println(isSparse(3));
    }

    public static boolean isSparse(int n)
    {
       return (n & (n<<1)) == 0;
    }
}
