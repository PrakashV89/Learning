package com.geeksforgeeks;

public class DigitalRoot {
    public static void main(String[] args) {
        System.out.println(digitalRoot(9999));
    }

    public static int digitalRoot(int n) {
        if (n < 10)
            return n;
        else
            return digitalRoot(n % 10 + n / 10);
    }
}
