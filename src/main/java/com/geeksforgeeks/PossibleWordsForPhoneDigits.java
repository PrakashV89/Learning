package com.geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PossibleWordsForPhoneDigits {
    private static final String[][] DIALPAD_MAP_ARR = { { "0" }, { "1" }, { "A", "B", "C" }, { "D", "E", "F" },
            { "G", "H", "I" }, { "J", "K", "L" }, { "M", "N", "O" }, { "P", "Q", "R", "S" }, { "T", "U", "V" },
            { "W", "X", "Y", "Z" } };

    public static void main(String[] args) {
        System.out.println(possibleWordsItr(new int[] { 2, 3, 4 }, 3));
    }

    // Function to find list of all words possible by pressing given numbers.
    static ArrayList<String> possibleWords(int a[], int N) {
        ArrayList<String> lexicographicallySortedList = possibleWords(a, N, 0, "");
        Collections.sort(lexicographicallySortedList);
        return lexicographicallySortedList;
    }

    static ArrayList<String> possibleWords(int a[], int N, int i, String str) {
        ArrayList<String> list = new ArrayList<>();
        if (i == N) {
            list.add(str.toLowerCase());
            return list;
        }
        for (int j = 0; j < DIALPAD_MAP_ARR[a[i]].length; j++) {
            list.addAll(possibleWords(a, N, i + 1, str + DIALPAD_MAP_ARR[a[i]][j]));
        }

        return list;
    }

    static ArrayList<String> possibleWordsItr(int[] a, int N) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list.add("");

        int i = 0;
        while (i < N) {
            ArrayList<String> list1 = new ArrayList<>();
            for (int j = 0; j < DIALPAD_MAP_ARR[a[i]].length; j++) {
                for (String str : list) {
                    list1.add(str + DIALPAD_MAP_ARR[a[i]][j].toLowerCase());
                    if (i == N - 1 && str.length() == N - 1) {
                        list2.add(str + DIALPAD_MAP_ARR[a[i]][j].toLowerCase());
                    }
                }
            }

            list.addAll(list1);
            ++i;
        }

        Collections.sort(list2);
        return list2;
    }

    static ArrayList<String> possibleWordsItrBest(int[] a, int N) {
        ArrayList<String> list2 = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        queue.add("");

        while (!queue.isEmpty()) {
            String element = queue.remove();

            if (element.length() == N) {
                list2.add(element);
            } else {
                for (int i = 0; i < DIALPAD_MAP_ARR[a[element.length()]].length; i++) {
                    queue.add(element + DIALPAD_MAP_ARR[a[element.length()]][i].toLowerCase());
                }
            }
        }

        Collections.sort(list2);
        return list2;
    }
}
