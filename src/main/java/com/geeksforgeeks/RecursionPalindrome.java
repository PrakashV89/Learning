package com.geeksforgeeks;

public class RecursionPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("malayalam"));
    }

    private static boolean isPalindrome(String string) {
        return isPalindrome(string, string.length());
    }

    private static boolean isPalindrome(String string, int length) {
        if (length == 0 || length == 1) {
            return true;
        }
        return isPalindrome1(string, 1, length);
    }

    private static boolean isPalindrome1(String str, int i, int length) {
        if (i >= length)
            return true;

        if (str.charAt(i - 1) != str.charAt(length - 1)) {
            return false;
        }

        return str.charAt(i - 1) == str.charAt(length - 1) && isPalindrome1(str, i + 1, length - 1);
    }

    public static boolean isPalindrome1stMethod(String str) {
        return isPalindrome(str, 1, str.length());
    }

    private static boolean isPalindrome(String str, int i, int j) {
        if (str.charAt(i - 1) != str.charAt(j - 1)) {
            return false;
        }

        if (i == str.length() / 2) {
            return true;
        }

        return isPalindrome(str, i + 1, j - 1);
    }
}
