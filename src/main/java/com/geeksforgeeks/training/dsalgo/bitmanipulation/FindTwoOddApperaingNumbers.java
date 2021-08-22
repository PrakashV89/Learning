package com.geeksforgeeks.training.dsalgo.bitmanipulation;

public class FindTwoOddApperaingNumbers {
    public static void main(String[] args) {
        print(new int[] { 3, 4, 3, 4, 8, 4, 4, 32, 7, 7 });
    }

    // Find Right Most set Bit in xor => xor & ~(xor-1)
    private static void print(int[] is) {
        int xor = 0;
        for (int i : is) {
            xor ^= i;
        }

        int rightMostSetBit = xor & ~(xor - 1);
        int xorOfNumsWhereRightMostBitIsSet = 0;
        int xorOfNumsWhereRightMostBitIsNotSet = 0;
        for (int i : is) {
            if ((i & rightMostSetBit) != 0) {
                xorOfNumsWhereRightMostBitIsSet ^= i;
            } else {
                xorOfNumsWhereRightMostBitIsNotSet ^= i;
            }
        }

        System.out.println(xorOfNumsWhereRightMostBitIsSet + " " + xorOfNumsWhereRightMostBitIsNotSet);
    }

    private static void printNaive(int[] is) {
        for (int i = 0; i < is.length; i++) {
            int count = 0;
            for (int j = 0; j < is.length; j++) {
                if (is[i] == is[j]) {
                    count++;
                }
            }
            if (count % 2 != 0) {
                System.out.println(is[i]);
            }
        }
    }
}
