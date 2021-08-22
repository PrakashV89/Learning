package com.geeksforgeeks;

public class N_Bonacci {
    public static void main(String[] args) {
        print(2, 10);
        print(3, 10);
        print(4, 10);
    }

    private static void printUsingGFG(int n, int m) {
        int[] arr = new int[m];
        for (int i = 0; i < n - 1; i++) {
            System.out.printf("%d ", arr[i]);
        }
        arr[n - 1] = 1;
        arr[n] = 1;
        System.out.printf("%d %d", 1, 1);
        for (int i = n + 1; i <= m; i++) {
            arr[i] = 2 * arr[i - 1] - arr[i - n - 1];
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    private static void print(int n, int m) {
        int[] arr = new int[m + 1];
        int index = 0;
        for (int i = 1; i < n; i++) {
            arr[index++] = 0;
            System.out.printf("%d ", 0);
        }
        arr[n] = 1;
        System.out.printf("%d ", 1);
        for (int i = n + 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i] += arr[i - j];
            }
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
}
