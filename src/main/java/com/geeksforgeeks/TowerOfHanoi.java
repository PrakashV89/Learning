package com.geeksforgeeks;

import java.util.Arrays;
import java.util.Stack;

public class TowerOfHanoi {
    private static char s;

    public static void main(String[] args) {

        /*
         * int[][] towerOfHanoi = { { 3, 2, 0 }, { 1, 0, 0 }, { 0, 0, 0 } }; //
         * solve(towerOfHanoi, 0);
         * 
         * // solve(new int[][] { { 3, 0, 0 }, { 2, 1, 0 }, { 0, 0, 0 } }, 0); solve(new
         * int[][] { { 3, 2, 1 }, { 0, 0, 0 }, { 0, 0, 0 } }, 0);
         */
        Stack<Integer> A = new Stack<>();

        solveToh(4);
    }

    private static int solveToh(int n) {
        Stack<Integer> Source = new Stack<>();
        Stack<Integer> Destination = new Stack<>();
        Stack<Integer> Aux = new Stack<>();
        char s = 'S';
        char d = 'D';
        char a = 'A';

        if ((n & 1) == 0) {
            char tmp = d;
            d = a;
            a = tmp;
        }

        for (int i = n; i > 0; i--) {
            Source.push(i);
        }

        int numberOfMoves = 0;

        while ((((n & 1) != 0 && Destination.size() < n)) || ((n & 1) == 0 && Aux.size() < n)) {
            ++numberOfMoves;
            switch (numberOfMoves % 3) {
                case 1:
                    /* if ((n & 1) == 1) { */
                    moveDiscs(Source, Destination, s, d);
                    /*
                     * } else { moveDiscs(Source, Aux, s, a); }
                     */
                    break;
                case 2:
                    /* if ((n & 1) == 1) { */
                    moveDiscs(Source, Aux, s, a);
                    /*
                     * } else {
                     * 
                     * moveDiscs(Source, Destination, s, d); }
                     */
                    break;
                case 0:
                    /* if ((n & 1) == 1) { */
                    moveDiscs(Aux, Destination, a, d);
                    /*
                     * } else { moveDiscs(Aux, Destination, a, d); }
                     */
                    break;
            }

        }
        System.out.println("Done....");
        return numberOfMoves;
    }

    private static void moveDiscs(Stack<Integer> source, Stack<Integer> destination, char s, char d) {
        if (source.isEmpty()) {
            System.out.println("Move " + destination.peek() + " from " + d + " to " + s);
            source.push(destination.pop());
        } else if (destination.isEmpty()) {
            System.out.println("Move " + source.peek() + " from " + s + " to " + d);
            destination.push(source.pop());
        } else if (source.peek() > destination.peek()) {
            System.out.println("Move " + destination.peek() + " from " + d + " to " + s);
            source.push(destination.pop());

        } else {
            System.out.println("Move " + source.peek() + " from " + s + " to " + d);
            destination.push(source.pop());
        }
        /*
         * if (destination.isEmpty() || (!source.isEmpty() && destination.peek() >
         * source.peek())) { System.out.println("Move " + source.peek() + " from " + s +
         * " to " + d); destination.push(source.pop()); } else {
         * System.out.println("Move " + destination.peek() + " from " + d + " to " + s);
         * source.push(destination.pop()); }
         */
    }

    private static void solve(int[][] arr, int numberOfSteps) {
        int[] a = arr[0];
        int[] b = arr[1];
        int[] aux = arr[2];

        int aIndex = 2;
        int bIndex = 0;
        int auxIndex = 0;

        while (true) {

            if (a[aIndex] != 0) {
                if (b[bIndex] == 0) {
                    if (a[aIndex] == 3) {
                        b[bIndex++] = a[aIndex];
                        a[aIndex] = 0;
                        if (aIndex > 0) {
                            --aIndex;
                        }
                        numberOfSteps++;
                    } else {
                        aux[auxIndex++] = a[aIndex];
                        a[aIndex] = 0;
                        if (aIndex > 0) {
                            --aIndex;
                        }
                        numberOfSteps++;
                    }
                } else {
                    if (b[bIndex] > a[aIndex]) {
                        b[++bIndex] = a[aIndex];
                        a[aIndex] = 0;
                        if (aIndex > 0) {
                            --aIndex;
                        }
                        numberOfSteps++;
                    } else {

                        aux[auxIndex++] = b[bIndex];
                        b[bIndex] = a[aIndex];
                        a[aIndex] = 0;
                        if (aIndex > 0) {
                            --aIndex;
                        }
                        numberOfSteps++;
                    }
                }
            }

            if (b[bIndex] != 0 && b[bIndex] != 3 && a[0] == 0) {
                if (aux[auxIndex] == 0) {
                    aux[auxIndex++] = b[bIndex];
                    b[bIndex] = 0;
                    if (bIndex > 0) {
                        --bIndex;
                    }
                    numberOfSteps++;
                } else {
                    if (aux[auxIndex] > b[bIndex]) {
                        aux[++auxIndex] = b[bIndex];
                        b[bIndex--] = 0;
                        if (bIndex > 0) {
                            --bIndex;
                        }
                        numberOfSteps++;
                    } else {
                        b[++bIndex] = aux[auxIndex];
                        aux[auxIndex] = 0;
                        if (auxIndex > 0) {
                            --auxIndex;
                        }
                        numberOfSteps++;
                    }
                }
            } else {
                if (b[bIndex] != 0 && a[0] == 0) {
                    if (aux[auxIndex] < b[bIndex]) {
                        if (b[bIndex] - aux[auxIndex] == 1) {
                            b[++bIndex] = aux[auxIndex];
                            aux[auxIndex] = 0;
                            if (auxIndex > 0) {
                                --auxIndex;
                            }
                            numberOfSteps++;
                        } else {
                            if (a[aIndex] == 0) {
                                a[aIndex] = aux[auxIndex];
                                aux[auxIndex] = 0;
                                if (auxIndex > 0) {
                                    --auxIndex;
                                }
                                numberOfSteps++;
                            }
                        }
                    }
                }
            }

            if (a[0] == 0 && b[2] != 0) {
                break;
            } else {
                if (aIndex > 0) {
                    aIndex--;
                }
            }

        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(aux));
        System.out.println("Number Of Steps : " + numberOfSteps);
    }

    /*
     * 1. Only 1 disc at a time 2. No Longer disc over smaller 3. Only the top disc
     * of the tower can be moved
     */

    /*
     * private static void solve(int[][] towerOfHanoi, int numberOfSteps) { int
     * coneIndex = -1; int discIndex = -1; int maxCount = 0; for (int i = 0; i <
     * towerOfHanoi.length; i++) { int count = 0; for (int j = 0; j <
     * towerOfHanoi[i].length; j++) { if (towerOfHanoi[i][j] != 0) { count++; } else
     * { if (count > maxCount || ((j > 0) && towerOfHanoi[coneIndex][discIndex] <
     * towerOfHanoi[i][j - 1])) { coneIndex = i; discIndex = j - 1; maxCount =
     * count; } } } }
     * 
     * int numOfSteps = 0; for (int i = 0; i < towerOfHanoi.length; i++) { if (i !=
     * coneIndex) { for (int j = 0; j < towerOfHanoi[i].length; j++) { if (discIndex
     * + 1 < towerOfHanoi[i].length && towerOfHanoi[coneIndex][discIndex + 1] == 0
     * && towerOfHanoi[i][j] != 0 && towerOfHanoi[i][j] <
     * towerOfHanoi[coneIndex][discIndex] && towerOfHanoi[coneIndex][discIndex] -
     * towerOfHanoi[i][j] == 1) { towerOfHanoi[coneIndex][++discIndex] =
     * towerOfHanoi[i][j]; towerOfHanoi[i][j] = 0; numOfSteps++; } } } }
     * 
     * for (int i = 0; i < towerOfHanoi.length; i++) { if (i != coneIndex) { for
     * (int j = 0; j < towerOfHanoi[i].length; j++) { if (discIndex + 1 <
     * towerOfHanoi[i].length && towerOfHanoi[coneIndex][discIndex + 1] == 0 &&
     * towerOfHanoi[i][j] != 0 && towerOfHanoi[i][j] <
     * towerOfHanoi[coneIndex][discIndex] && towerOfHanoi[coneIndex][discIndex] -
     * towerOfHanoi[i][j] == 1) { towerOfHanoi[coneIndex][++discIndex] =
     * towerOfHanoi[i][j]; towerOfHanoi[i][j] = 0; numOfSteps++; } } } }
     * 
     * for (int i = 0; i < towerOfHanoi.length; i++) {
     * System.out.println(Arrays.toString(towerOfHanoi[i])); }
     * 
     * System.out.println("Number Of Steps :" + numOfSteps); // solve(towerOfHanoi,
     * numberOfSteps++); }
     */

    public static int solve(int n) {
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        Stack<Integer> C = new Stack<>();
        for (int i = n; i > 0; i--) {
            A.push(i);
        }
        System.out.println(A);

        int numberOfMoves = 0;
        numberOfMoves += moveAToBWithCAux(n - 1, A, B, C, 'a', 'b', 'c');

        System.out.println("Move " + A.peek() + " from " + 'a' + " to " + 'c');
        C.push(A.pop());
        numberOfMoves++;
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        numberOfMoves += moveBToCWithAAux(n - 1, B, C, A, 'b', 'c', 'a');

        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

        System.out.println("Number Of Moves : " + numberOfMoves);
        return numberOfMoves;

    }

    private static int moveAToBWithCAux(int n, Stack<Integer> A, Stack<Integer> B, Stack<Integer> C, char a, char b,
            char c) {
        int numberOfMoves = 0;
        for (int i = n; i > 0; i--) {
            if (B.isEmpty() || B.peek() > A.peek()) {
                System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                B.push(A.pop());
                numberOfMoves++;
            } else {
                if (C.isEmpty() || C.peek() > A.peek()) {
                    System.out.println("Move " + A.peek() + " from " + a + " to " + c);
                    C.push(A.pop());
                    numberOfMoves++;
                    System.out.println("Move " + B.peek() + " from " + b + " to " + a);
                    A.push(B.pop());
                    numberOfMoves++;
                    System.out.println("Move " + C.peek() + " from " + c + " to " + b);
                    B.push(C.pop());
                    numberOfMoves++;
                    System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                    B.push(A.pop());
                    numberOfMoves++;
                }
            }
        }

        return numberOfMoves;
    }

    private static int moveBToCWithAAux(int n, Stack<Integer> A, Stack<Integer> B, Stack<Integer> C, char a, char b,
            char c) {
        int numberOfMoves = 0;
        for (int i = n; i > 0 && B.size() < n + 1; i--) {
            if (B.isEmpty() || (B.peek() > A.peek() && B.peek() - A.peek() == 1)) {
                System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                B.push(A.pop());
                numberOfMoves++;
            } else if ((B.peek() > A.peek() && B.peek() - A.peek() > 1)) {
                System.out.println("Move " + A.peek() + " from " + a + " to " + c);
                C.push(A.pop());
                numberOfMoves++;
                System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                B.push(A.pop());
                numberOfMoves++;
                System.out.println("Move " + C.peek() + " from " + c + " to " + b);
                B.push(C.pop());
                numberOfMoves++;
            } else {
                if (C.isEmpty() || C.peek() > A.peek()) {
                    System.out.println("Move " + A.peek() + " from " + a + " to " + c);
                    C.push(A.pop());
                    numberOfMoves++;
                    System.out.println("Move " + B.peek() + " from " + b + " to " + a);
                    A.push(B.pop());
                    numberOfMoves++;
                    System.out.println("Move " + C.peek() + " from " + c + " to " + b);
                    B.push(C.pop());
                    numberOfMoves++;
                    System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                    B.push(A.pop());
                    numberOfMoves++;
                }
            }
        }

        return numberOfMoves;
    }

    private static int moveAToBWithCAuxFromNToI(int n, Stack<Integer> A, Stack<Integer> B, Stack<Integer> C, char a,
            char b, char c) {
        int numberOfMoves = 0;
        for (int i = n; i > 0; i--) {
            if (B.isEmpty() || B.peek() > A.peek()) {
                System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                B.push(A.pop());
                numberOfMoves++;
                System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                B.push(A.pop());
                numberOfMoves++;

            } else if (B.peek() > A.peek()) {
                if (C.isEmpty() || C.peek() > A.peek()) {
                    System.out.println("Move " + A.peek() + " from " + a + " to " + c);
                    C.push(A.pop());
                    numberOfMoves++;
                    System.out.println("Move " + B.peek() + " from " + b + " to " + a);
                    A.push(B.pop());
                    numberOfMoves++;
                    System.out.println("Move " + C.peek() + " from " + c + " to " + b);
                    B.push(C.pop());
                    numberOfMoves++;
                    System.out.println("Move " + A.peek() + " from " + a + " to " + b);
                    B.push(A.pop());
                    numberOfMoves++;
                }
            }
        }

        return numberOfMoves;
    }
}
