package com.geeksforgeeks.firstsolution.recursion;

public class PalindromeOrNot {
    public static void main(String[] args) {
        System.out.println(isPalindrome("ABBBA"));
        System.out.println(isPalindrome("ABABA"));
        System.out.println(isPalindrome("ABBA"));
        System.out.println(isPalindrome("ABAA"));
    }

    private static boolean isPalindrome(String str){
        return isPalindrome(str, 0, str.length()-1);
    }

    private static boolean isPalindrome(String str, int start , int end) {
        if(start >= end){
            return true;
        }
        if(str.charAt(start) == str.charAt(end)) {
            return isPalindrome(str, ++start, --end);
        }
        return false;
    }
}
