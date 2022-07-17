package com.java.collection.iteration;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a","b","c","d"));

        ListIterator<String> listItr = list.listIterator();

        while(listItr.hasNext()){
            final String next = listItr.next();
            System.out.println(next);
            if(next.equals("c")){
//                listItr.remove();
                listItr.set("e");
            }

            if(next.equals("d")){
                listItr.remove();
                listItr.add("f");
            }
        }

        System.out.println("Next Index : " + listItr.nextIndex());

        while(listItr.hasPrevious()){
            System.out.println(listItr.previous());
        }

        System.out.println("Previous Index : " + listItr.previousIndex());
    }
}
