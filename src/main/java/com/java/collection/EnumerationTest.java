package com.java.collection;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class EnumerationTest {
    public static void main(String[] args) {
        Vector<String> list = new Vector<>(Arrays.asList("a", "b", "c", "d"));
        Enumeration<String> enumeration = list.elements();

        while(enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
    }
}
