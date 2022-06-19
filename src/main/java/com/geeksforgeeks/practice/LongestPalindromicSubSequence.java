package com.geeksforgeeks.practice;

import java.util.Arrays;

public class LongestPalindromicSubSequence {
    public static void main(String[] args) {
        String question = "bbabcbcab";
        System.out.println(new LongestPalindromicSubSequence().longestPalindromeSequence(question));
    }

    public int longestPalindromeSequence(String question) {
        int[][] answer = new int[question.length()][question.length()];

        /*for (int i = 0; i < question.length(); i++) {
            answer[i] = new int[question.length()];
            for (int j = 0; j < question.length(); j++) {
                answer[i][j] = 1;
            }
        }*/

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer.length - i; j++) {
                int k = i + j;
                System.out.println( j + " : " + k);
                if(j == k){
                    answer[j][k] = 1;
                    //System.out.println(j + " :" + k);
                }
                else if(question.charAt(j) == question.charAt(k)){
                    System.out.println(String.format("## Getting value from %d and %d", j+1, k-1));
                    answer[j][k] = answer[j+1][k-1] + 2;
                }
                else{
                    final int a = answer[j][k - 1];
                    final int b = answer[j + 1][k];
                    if(a > b){
                        System.out.println(String.format("-- Getting value from %d and %d", j, k-1));
                    }
                    else{
                        System.out.println(String.format("@@ Getting value from %d and %d", j+1, k));
                    }
                    answer[j][k] = Math.max(a, b);
                }
            }
            System.out.print("  ");
            for (int l = 0; l < answer.length; l++) {
                System.out.print(" " + question.charAt(l) + " ");
            }
            System.out.println();
            for (int m = 0; m < answer.length; m++) {
                System.out.println(question.charAt(m) + " " + Arrays.toString(answer[m]));
            }
        }

        System.out.print("  ");
        for (int i = 0; i < answer.length; i++) {
             System.out.print(" " + question.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < answer.length; i++) {
            System.out.println(question.charAt(i) + " " + Arrays.toString(answer[i]));
        }
        return answer[0][question.length() - 1];
    }

    public int longestPalinSubseq(String s) {
        int max = Integer.MIN_VALUE;
        max = Math.max(max, longestPalinSubseq(s, 0, s.length() - 1));
        return max;
    }

    private int longestPalinSubseq(String s, int i, int j) {
        if (i == j) {
            return 1;
        }

        if (s.charAt(i) == s.charAt(j)) {
            return longestPalinSubseq(s, i + 1, j - 1) + 2;
        }
        return Math.max(longestPalinSubseq(s, i + 1, j), longestPalinSubseq(s, i, j - 1));
    }
}
