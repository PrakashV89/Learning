package com.geeksforgeeks.training.dsalgo.bitmanipulation;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSpan {
    public static void main(String[] args) {
        int[] a = new int[] { 0, 0, 1, 0, 1, 1, 1, 1,1,0 };
        int[] b = new int[] { 1, 1, 1, 0, 1, 0, 1, 1,1, 0 };
        /* int n = a.length;
        for (int i = 0; i < n; i++) {
            int xor = 0;
            int j = n - 1;
            for (; j >= i; j--) {
                xor ^= a[j] ^ b[j];
            }
            if (xor == 0) {
                System.out.println(i + " " + j);
                break;
            }
        } */

        int[] xor = new int[a.length];
        for (int i = 0; i < xor.length; i++) {
            xor[i] = a[i ]^ b[i];
        }

        int i = 0,j = 0, max = 0, maxi = 0, maxj = 0;
        for (int j2 = 0; j2 < xor.length; j2++) {
            if(j2 +1 < xor.length){
                if(xor[j2] != xor[j2 + 1]){
                    if(xor[j2+1] == 0){
                        i = j2 + 1;
                        j = -1;
                    }
                    else{
                        if(i != -1){
                            j = j2;
                            int tmpMax = Math.max(j - i, max);
                            if(tmpMax != max){
                                maxi = i;
                                maxj = j;
                                max = tmpMax;
                            } 
                        }
                    }
                }
            }
            else{
                if(i != -1){
                    j = j2;
                    int tmpMax = Math.max(j - i, max);
                    if(tmpMax != max){
                        maxi = i;
                        maxj = j;
                        max = tmpMax;
                    } 
                }
            }
        }

        System.out.println(Arrays.toString(xor));
        System.out.println(i + " " + j);

        System.out.println(maxi + " " + maxj);

    }

    static int arr1[] = new int[]{0, 1, 0, 1, 1, 1, 1};
    static int arr2[] = new int[]{1, 1, 1, 1, 1, 0, 1};
     
    // Returns length of the longest common sum in arr1[]
    // and arr2[]. Both are of same size n.
    static int longestCommonSum(int n)
    {
        // Initialize result
        int maxLen = 0;
      
        // One by one pick all possible starting points
        // of subarrays
        for (int i=0; i<n; i++)
        {
           // Initialize sums of current subarrays
           int sum1 = 0, sum2 = 0;
      
           // Conider all points for starting with arr[i]
           for (int j=i; j<n; j++)
           {
               // Update sums
               sum1 += arr1[j];
               sum2 += arr2[j];
      
               // If sums are same and current length is
               // more than maxLen, update maxLen
               if (sum1 == sum2)
               {
                 int len = j-i+1;
                 if (len > maxLen)
                    maxLen = len;
               }
           }
        }
        return maxLen;
    }
    static int longestCommonSumAuxArray(int n)
    {
        // Initialize result
        int maxLen = 0;
      
        // Initialize prefix sums of two arrays
        int preSum1 = 0, preSum2 = 0;
      
        // Create an array to store staring and ending
        // indexes of all possible diff values. diff[i]
        // would store starting and ending points for
        // difference "i-n"
        int diff[] = new int[2*n+1];
      
        // Initialize all starting and ending values as -1.
        for (int i = 0; i < diff.length; i++) {
            diff[i] = -1;
        }
      
        // Traverse both arrays
        for (int i=0; i<n; i++)
        {
            // Update prefix sums
            preSum1 += arr1[i];
            preSum2 += arr2[i];
      
            // Comput current diff and index to be used
            // in diff array. Note that diff can be negative
            // and can have minimum value as -1.
            int curr_diff = preSum1 - preSum2;
            int diffIndex = n + curr_diff;
      
            // If current diff is 0, then there are same number
            // of 1's so far in both arrays, i.e., (i+1) is
            // maximum length.
            if (curr_diff == 0)
                maxLen = i+1;
      
            // If current diff is seen first time, then update
            // starting index of diff.
            else if ( diff[diffIndex] == -1)
                diff[diffIndex] = i;
      
            // Current diff is already seen
            else
            {
                // Find length of this same sum common span
                int len = i - diff[diffIndex];
      
                // Update max len if needed
                if (len > maxLen)
                    maxLen = len;
            }
        }
        return maxLen;
    }
     
    static int longestCommonSumHashing(int[] arr1, int[] arr2, int n)
    {
        // Find difference between the two
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = arr1[i] - arr2[i];
 
        // Creates an empty hashMap hM
        HashMap<Integer, Integer> hM = new HashMap<>();
 
        int sum = 0;     // Initialize sum of elements
        int max_len = 0; // Initialize result
 
        // Traverse through the given array
        for (int i = 0; i < n; i++)
        {
            // Add current element to sum
            sum += arr[i];
 
            // To handle sum=0 at last index
            if (sum == 0)
                max_len = i + 1;
 
            // If this sum is seen before,
            // then update max_len if required
            if (hM.containsKey(sum))
                max_len = Math.max(max_len, i - hM.get(sum));
             
            else // Else put this sum in hash table
                hM.put(sum, i);
        }
        return max_len;
    }

}
