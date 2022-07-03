package com.geeksforgeeks.firstsolution.recursion;

public class GenerateSubSets {
    public static void main(String[] args) {
        generate("ABC");
    }

    private static void generate(String s) {
        generate(s, 0, "");
    }

    private static void generate(String s, int i, String curr) {
        if(s.length() == i){
            System.out.println(curr);
            return;
        }

        generate(s, i+1, curr);
        generate(s, i+1, curr+s.charAt(i));
    }
}
