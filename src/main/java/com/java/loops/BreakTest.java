package com.java.loops;

public class BreakTest {
    public static void main(String[] args) {
        System.out.println("Outer Label Before...");
        outer_loop:
        {
            System.out.println("Outer Label After...");
            for (int i = 0; i < 10; i++) {
                System.out.println("@@ : " + i);
                inner_loop:
                System.out.println("dsadsa");
                for (int j = 0; j < 10; j++) {
                    System.out.println("$$ : " + j);
                    break outer_loop;
                }
            }
        }
        System.out.println("");
        outer_loop_1:
//            System.out.println("Outer Label After...");
            for (int i = 0; i < 10; i++) {
                System.out.println("@@ : " + i);
                inner_loop_1:
                System.out.println("dsadsa");
                for (int j = 0; j < 10; j++) {
                    System.out.println("$$ : " + j);
                    continue outer_loop_1;
                }
            }


        System.out.println("Loop Done");
    }
}
