package com.geeksforgeeks.practice;
// { Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;

/**
 * 14 8
 * 7 9 10
 * 10 2 9
 * 5 8 7
 * 9 3 1
 * 3 10 6
 * 14 5 2
 * 4 7 2
 * 1 4 8
 */
class WaterConnectionProblem{
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0){
            int n = sc.nextInt();
            int p = sc.nextInt();
            ArrayList<Integer> a = new ArrayList<Integer>();
            ArrayList<Integer> b = new ArrayList<Integer>();
            ArrayList<Integer> d = new ArrayList<Integer>();

            for(int i=0;i<p;i++)
            {
                a.add(sc.nextInt());
                b.add(sc.nextInt());
                d.add(sc.nextInt());
            }

            Solution ob = new Solution();
            ArrayList<ArrayList<Integer>> ans = ob.solve(n, p, a, b, d);
            System.out.println(ans.size());
            for (int i=0;i<ans.size();i++){
                System.out.println(ans.get(i).get(0)+" "+ans.get(i).get(1)+" "+ans.get(i).get(2));
            }
        }
    }
} // } Driver Code Ends


//User function Template for Java
class Solution
{
    ArrayList<ArrayList<Integer>> solve(int n, int p, ArrayList<Integer> a ,ArrayList<Integer> b ,ArrayList<Integer> d)
    {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int[] arr = new int[n+1];
        int[] arr1 = new int[n+1];
        int[] arr2 = new int[n+1];
        for(int i=0;i<a.size();i++){
            arr[a.get(i)]=b.get(i);
            arr1[b.get(i)] = a.get(i);
            if(d.get(i) > 0){
                arr2[a.get(i)]=d.get(i);
            }
            else{
                arr2[a.get(i)]=Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<=n; i++){
            if(arr[i] !=0 && arr1[i] == 0){
                int j = i;
                int min = arr2[j];
                int lastJ = 0;
                while(arr[j] != 0){
                    j = arr[j];
                    if(arr2[j] != 0){
                        min = Math.min(min, arr2[j]);
                    }
                    lastJ = arr[j];
                }
                ArrayList<Integer> subList = new ArrayList<>();
                subList.add(i);
                subList.add(j);
                subList.add(min);
                list.add(subList);
            }

        }

        return list;
    }
}
