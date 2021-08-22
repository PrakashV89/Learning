package com.geeksforgeeks;

import static java.lang.System.out;

public class MinimumConsecutiveFlips {
    public static void main(String[] args) {
        int[] arr = { 1, 1, 0, 0, 0, 1 };
        getUsingGFG(arr);
        getUsingGFG(new int[] { 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1 });
        getUsingGFG(new int[] { 1, 1, 1 });
        getUsingGFG(new int[] { 0, 1 });

    }

    private static void getUsingGFG(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                if (arr[i] != arr[0]) {
                    out.printf("From %s to ", i);
                    if (i == arr.length - 1) {
                        out.printf("%s\n", i);
                    }
                } else {
                    out.printf("%s\n", i - 1);
                }
            }
        }

    }

    private static void get(int[] arr) {
        int binaryDigitToBeFlipped = -1;

        for (int i = 1; i < arr.length; i++) {
            if (binaryDigitToBeFlipped == -1) {
                if (arr[i] != arr[i - 1]) {
                    binaryDigitToBeFlipped = arr[i];
                    out.printf("From %s ", i);
                    if (i == arr.length - 1) {
                        out.printf("to %s\n", i);
                    }
                }
            } else {
                if (arr[i] != arr[i - 1]) {
                    if (arr[i] != binaryDigitToBeFlipped) {
                        out.printf("to %s\n", i - 1);
                    } else {
                        out.printf("From %s ", i);
                        if (i == arr.length - 1) {
                            out.printf("to %s\n", i);
                        }
                    }
                }
            }

        }
    }

    private static void naiveSolution(int[] arr) {
        int numberOfFlipsForOne = 0;
        int numberOfFlipsForZero = 0;

        if (arr[0] == 1) {
            numberOfFlipsForOne++;
        } else {
            numberOfFlipsForZero++;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 1 && arr[i - 1] == 1) {
                numberOfFlipsForOne++;
            } else if (arr[i] == 0 && arr[i - 1] == 0) {
                numberOfFlipsForZero++;
            } else {
                if (arr[i] == 1) {
                    numberOfFlipsForOne++;
                } else {
                    numberOfFlipsForZero++;
                }
            }
        }

        int binaryDigitToBeFlipped = numberOfFlipsForZero <= numberOfFlipsForOne
                ? ((numberOfFlipsForOne == numberOfFlipsForZero) ? arr[0] : 0)
                : 1;

        int from = -1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == binaryDigitToBeFlipped) {
                if (from == -1) {
                    from = i;
                } else {
                    if (arr[i + 1] != arr[i]) {
                        out.printf("Flip from %d to %d\n", from, i);
                        from = -1;
                    }
                }
            }
        }

        if (from != -1) {
            out.printf("Flip from %d to %d\n", from, arr.length - 1);
        }
    }
}
