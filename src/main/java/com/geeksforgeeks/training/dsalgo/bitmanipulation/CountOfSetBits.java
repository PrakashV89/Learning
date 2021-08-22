package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class CountOfSetBits {
    public static void main(String[] args) {
        System.out.println(countSetBitsMathPowApproach(4));
    }

    public static int countSetBits(int n) {
        n++;
        int countOfBits = 0;
        int totalBits = getTotalBitsForN(n);

        for (int i = 1; i <= totalBits; i++) {
            int pairSize = (int) Math.pow(2, i);

            if (pairSize <= n) {
                int totalNoOfPairs = n / pairSize;
                int totalNumberOf1s = totalNoOfPairs * (pairSize / 2);

                if (n % pairSize != 0) {
                    int remainingPair = n % pairSize;
                    int only0s = pairSize / 2;
                    if (remainingPair > only0s) {
                        totalNumberOf1s += remainingPair - only0s;
                    }
                }
                System.out.println(totalNumberOf1s);
                countOfBits += totalNumberOf1s;
            } else {
                int only0s = pairSize / 2;
                int totalNumberOf1s = n - only0s;

                System.out.println(totalNumberOf1s);
                countOfBits += totalNumberOf1s;
            }
        }

        return countOfBits;
    }

    static int getTotalBitsForN(int n) {
        return (int) (Math.floor(Math.log10(n) / Math.log10(2)) + 1);
    }

    static int countSetBitsMathPowApproach(int n) {
        if (n == 0)
            return 0;
        int x = (log2(n));
        return ((1<< (x-1)) * x) + (n-(1<<(x)) + 1) + countSetBitsMathPowApproach(n - (1<<(x)));
    }

    private static int log2(int n) {
        return (int) (Math.log10(n)/Math.log10(2));
    }
}
