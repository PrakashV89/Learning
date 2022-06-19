package com.geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    public static void main(String[] args) {
        System.out.println(powerSetIt("xxyzz"));
    }

    static List<String> powerSet(String s) {
        return powerSet(s, 0, "");
    }

    private static List<String> powerSet(String s, int i, String string) {
        List<String> combinations = new ArrayList<>();
        if (i == s.length()) {
            combinations.add(string);
            return combinations;
        }

        combinations.addAll(powerSet(s, i + 1, string));
        combinations.addAll(powerSet(s, i + 1, string + s.charAt(i)));

        return combinations;
    }

    // Wrong solution
    static List<String> powerSetIterative(String s) {
        ArrayList<String> combinations = new ArrayList<>();

        combinations.add("");
        for (int i = 0; i < s.length(); i++) {
            String ss = s.charAt(i) + "";
            String sss = ss;
            combinations.add("" + s.charAt(i));
            int numOfIterations = 0;
            for (int j = i + 1; j < s.length(); j++) {
                combinations.add(ss + s.charAt(j));
                sss += s.charAt(j);
                ++numOfIterations;
                if (numOfIterations > 1) {
                    combinations.add(sss);
                }
            }
            /*
             * if (numOfIterations > 1) { combinations.add(sss); }
             */
        }

        return combinations;
    }

    static ArrayList<String> powerSetRec(String s) {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        return powerSetRec(s, 0, list);
    }

    private static ArrayList<String> powerSetRec(String s, int i, ArrayList<String> list) {
        if (i == s.length()) {
            return list;
        }

        ArrayList list1 = new ArrayList<>();
        for (String element : list) {
            list1.add(element + s.charAt(i));
        }
        list1.addAll(list);
        return powerSetRec(s, ++i, list1);
    }

    static List<String> powerSetIt(String s) {
        int pow = 1 << s.length();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < pow; i++) {
            String str = "";
            for (int j = 0; j < s.length(); j++) {
                if ((i & 1 << j) != 0) {
                    str += s.charAt(j);
                }
            }
            list.add(str);
        }
        return list;
    }

}
