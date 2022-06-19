package com.geeksforgeeks;

import java.io.*;

public class MinimumNumberOfJumps {
    public static void main(String[] args) throws IOException {
        final String simpleName = MinimumNumberOfJumps.class.getSimpleName();
        System.out.println(simpleName);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("C:\\Users\\praka\\Projects-GFG\\ds-algo\\src\\main\\resources\\"+ simpleName + ".dat")));
        System.out.println(br.lines());

        int arr[] = new int[Integer.parseInt(br.readLine())];
        final String[] strArr = br.readLine().split(" ");
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        System.out.println(minJumps(arr, arr.length));

    }

    static int minJumps(int arr[], int n){
        if(n <= 1) //Only one column or less nothing to do
            return 0;
        if(arr[0] == 0) // cant move forward
            return -1;

        int steps = arr[0];
        int maxStepsPossible = arr[0];

        int jump = 1; //have taken a jump

        for (int i = 1; i < n; i++) {
            if(i == n-1){
                return jump;
            }
            maxStepsPossible = Math.max(maxStepsPossible, i+arr[i]
                    //Possible steps from ith index
                    );

            --steps; // Step Taken

            if(steps == 0){
                ++jump;
                steps = maxStepsPossible - i;//Steps Taken till now
                if( i >= steps)
                    return -1;

            }

        }

        return jump;
    }
}
