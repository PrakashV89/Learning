package com.geeksforgeeks;

public class RecursionTowerOfHanoi {
    public static void main(String[] args) {
        towerOfHanoi(3, 'A', 'B', 'C');
    }

    static void towerOfHanoi(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("Move " + n + " from " + a + " to " + c);
            return;
        }

        towerOfHanoi(n - 1, a, c, b);
        System.out.println("Move " + n + " from " + a + " to " + c);
        towerOfHanoi(n - 1, b, a, c);
    }
}
