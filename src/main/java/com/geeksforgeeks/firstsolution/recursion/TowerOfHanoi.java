package com.geeksforgeeks.firstsolution.recursion;

public class TowerOfHanoi {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(toh(n, 1, 2, 3));
    }

    private static int toh(int n, int from, int to, int aux) {
        if (n == 1) {
            System.out.printf("Move Disk %d from rod %d to rod %d\n", n, from, to);
            return (int) Math.pow(2, n) - 1;
        }

        toh(n - 1, from, aux, to);
        System.out.printf("Move Disk %d from rod %d to rod %d\n", n, from, to);
        toh(n - 1, aux, to, from);

        return (int) Math.pow(2, n) - 1;
    }
}
