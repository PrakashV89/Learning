package com.geeksforgeeks;

public class GenerateSubSets {
    public static void main(String[] args) {
        generate("ABC");
        generateUsingBitWise("ABC");

        generateUsingGFG("ABC", 0, "");
    }

    public static void generate(String str) {
        generate(str.toCharArray(), 0, str.length(), "");
    }

    private static void generate(char[] charArray, int start, int length, String str) {
        System.out.println(str);
        if (start == length) {
            return;
        }

        /* if (start == 0) { */
        for (int j = start; j < length; j++) {
            generate(charArray, j + 1, length, str + charArray[j]);
        }
        /*
         * } else { generate(charArray, start + 1, length, str + charArray[start]); }
         */

    }

    public static void generateUsingBitWise(String str) {

        int combinations = (int) Math.pow(2, str.length());

        for (int i = 0; i < combinations; i++) {
            for (int j = 0; j < str.length(); j++) {
                if ((i & (1 << j)) > 0) {
                    System.out.print(str.charAt(j));
                }
            }
            System.out.println();
        }

    }

    public static void generateUsingGFG(String str, int i, String curr) {
        if (i == str.length()) {
            System.out.println(curr);
            return;
        }

        generateUsingGFG(str, i + 1, curr);
        generateUsingGFG(str, i + 1, curr + str.charAt(i));
    }
}
