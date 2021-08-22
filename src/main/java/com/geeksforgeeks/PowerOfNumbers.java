package com.geeksforgeeks;

public class PowerOfNumbers {
    public static void main(String[] args) {
        System.out.println(power(12, 21));
    }

    private static final long MODULO = 100_000_000_7;

    static long power(int N, int R) {
        if (R == 0) {
            return 1;
        }

        if (R == 1) {
            return N;
        }
        long tmp = (power(N, R / 2) % MODULO);
        return ((tmp * tmp) % MODULO * (power(N, R % 2) % MODULO)) % MODULO;
    }
}
