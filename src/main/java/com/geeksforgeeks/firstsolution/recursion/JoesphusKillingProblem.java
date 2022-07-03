package com.geeksforgeeks.firstsolution.recursion;

public class JoesphusKillingProblem {
    public static void main(String[] args) {
        int n= 7;
        int k = 2;

        System.out.println(kill(n, k));
    }

    private static int kill(int n, int k) {
        if(n == 1)
            return 1;
        return (kill(n - 1, k) + k - 1)%n +1;
    }

    private static int kill___(int n, int k) { //Not working
        int curr = 1;
        for(int i = n; i >= 1; i--){
            if(curr+k-1 == n) {
                curr = ((curr + k - 1) % n) + 1;
            }
            else{
                curr = ((curr + k - 1) % n) + 1;
            }
        }
        return (curr == 1? n : (curr -1));
    }

    private static int kill__(int n, int k) {
        int a = (int)(Math.log10(n)/Math.log10(2));
        int l = n - (int)Math.pow(2, a);

        if(l == 0){
            return 1;
        }
        System.out.println(a + " : " + l);
        return (2*l) + 1;
    }

    private static int kill_(int n, int k) {

        if (n == 1){
            return 1;
        }

        return (kill_(n - 1, k) + k - 1) % n + 1;
    }
}
