package com.java.collection;

import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
    public static void main(String[] args) {
        List<String> list = List.of("a","b","c","d");

        ListIterator<String> listItr = list.listIterator();

        while(listItr.hasNext()){
            System.out.println(listItr.next());
        }

        System.out.println(listItr.nextIndex());

        while(listItr.hasPrevious()){
            System.out.println(listItr.previous());
        }

        System.out.println(listItr.previousIndex());
    }
}
