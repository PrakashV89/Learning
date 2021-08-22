package com.geeksforgeeks;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class RopeCuttingProblem {
    public static void main(String[] args) {
        System.out.println(cutRopes(5, 2, 5, 1, 0));
        System.out.println(cutRopes(23, 12, 9, 11, 0));
        System.out.println(cutRopes(5, 4, 2, 6, 0));
        System.out.println(cutRopes(9, 2, 2, 2, 0));
    }

    private static int cutRopes(int length, int a, int b, int c, int pieces) {
        if (length == 0) {
            return pieces;
        }
        if (length < 0) {
            return -1;
        }

        int cutLength = cutRopes(length - a, a, b, c, pieces);
        cutLength = Math.max(cutLength, cutRopes(length - b, a, b, c, pieces));
        cutLength = Math.max(cutLength, cutRopes(length - c, a, b, c, pieces));

        if (cutLength == -1) {
            return -1;
        }

        return cutLength + 1;
    }
}
