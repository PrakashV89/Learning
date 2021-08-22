package com.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecursionJosephusProblem {
    public static void main(String[] args) {
        /*
         * System.out.println(jos(14, 2)); System.out.println(josUsingKModNPlus1(14,
         * 2)); System.out.println(josephus(14, 2));
         * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         * System.out.println(jos(3, 2));
         * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         * System.out.println(jos(3, 3));
         * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         * System.out.println(jos(3, 4));
         * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         */
        System.out.println(josephusUsingIteration(5, 2));
        System.out.println(josephusUsingList(5, 2));
        System.out.println(Josephus(5, 2));
    }

    public static int jos(int n, int k) {
        if (k % n == 0) {
            return 0;
        }
        return (n - 1) + jos((k % n), k);
    }

    public static int josUsingKModNPlus1(int n, int k) {
        if (n == 1) {
            return 0;
        }
        // System.out.println(n - 1);
        return (josUsingKModNPlus1(n - 1, k) + k) % n;
    }

    static int josephus(int n, int k) {
        if (n == 1)
            return 1;
        else {
            /*
             * The position returned by josephus(n - 1, k) is adjusted because the recursive
             * call josephus(n - 1, k) considers the original position k%n + 1 as position 1
             */
            return (josephus(n - 1, k) + k - 1) % n + 1;
        }
    }

    static int josephusUsingList(int n, int k) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, n).forEach(list::add);
        return josephusUsingList(list, 0, k);
    }

    private static int josephusUsingList(List<Integer> circleOfN, int start, int k) {
        if (circleOfN.size() == 1) {
            return circleOfN.get(0);
        }

        start = (start + k) % circleOfN.size();
        circleOfN.remove(start);

        return josephusUsingList(circleOfN, start, k);
    }

    private static int josephusUsingIteration(int n, int k) {
        boolean[] circle = new boolean[n];
        int index = k - 1;
        int isAlive = n;
        while (isAlive > 1) {

            killPersonAtIndex(circle, index);
            --isAlive;

            int k1 = 0;
            int index1 = index;
            while (k1 < k) {

                index1 = index1 + 1 < n ? ++index1 : 0;
                if (!circle[index1]) {
                    ++k1;
                }

            }
            index = index1;
        }

        return index + 1;
    }

    private static void killPersonAtIndex(boolean[] circle, int index) {
        circle[index] = true;
    }

    static int Josephus(int n, int k) {
        k--;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = 1; // Makes all the 'n' people alive by
                        // assigning them value = 1
        }
        int cnt = 0, cut = 0, num = 1; // Cut = 0 gives the sword to 1st person.
        while (cnt < (n - 1)) // Loop continues till n-1 person dies.
        {
            while (num <= k) // Checks next (kth) alive persons.
            {
                cut++;
                cut = cut % n; // Checks and resolves overflow
                               // of Index.
                if (arr[cut] == 1) {
                    num++; // Updates the number of persons
                           // alive.
                }
            }
            num = 1; // refreshes value to 1 for next use.
            arr[cut] = 0; // Kills the person at postion of 'cut'
            cnt++; // Updates the no. of killed persons.
            cut++;
            cut = cut % n; // Checks and resolves overflow of Index.
            while (arr[cut] == 0) // Checks the next alive person the
                                  // sword is to be given.
            {
                cut++;
                cut = cut % n; // Checks and resolves overflow
                               // of Index.
            }
        }
        return cut + 1; // Output is the position of the last
                        // man alive(Index + 1);
    }

    /********************
     * THIS CODE IS PRESENTED BY SHISHANK RAWAT
     **************************/
}
