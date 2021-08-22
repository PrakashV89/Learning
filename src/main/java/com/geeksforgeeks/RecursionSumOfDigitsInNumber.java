package com.geeksforgeeks;

public class RecursionSumOfDigitsInNumber {
    public static void main(String[] args) {
        System.out.println(get(1123, 0));
    }

    static int get(int number, int sum) {
        if (number == 0) {
            return sum;
        }
        return get(number / 10, sum + (number % 10));
    }
}
