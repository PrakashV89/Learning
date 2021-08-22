package com.geeksforgeeks;

public class LuckyNumbers {
    public static void main(String[] args) {
        System.out.println(isLucky(1, 2));
        System.out.println(isLucky(2, 2));
        System.out.println(isLucky(5, 2));
        System.out.println(isLucky(19, 2));
    }

    public static boolean isLucky(int n, int i) {
        if (n == 0 && i > n) {
            return false;
        }
        if (n > 0 && i > n) {
            return true;
        }

        if (n % i == 0) {
            return false;
        }

        return isLucky(n - (n / i), ++i);
    }

    public static boolean isLuckyIterative(int n) {
        int i = 2, index = n;
        while (i <= index) {

            if (index % i == 0) {
                return false;
            }

            index = index - (index / i);
            ++i;
        }

        return n == 0 ? false : true;
    }
}
