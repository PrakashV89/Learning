package com.java.collection.iteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        Iterator<String> itr = list.iterator();

        while(itr.hasNext()){

            final String next = itr.next();
            if(next.equals("c"))
                itr.remove();


            System.out.println(next);
        }

        System.out.println("Next Iteration after removing c element");
        itr = list.iterator();
        while(itr.hasNext()){

            final String next = itr.next();
//            if(next.equals("c"))
            itr.remove();


            System.out.println(next);
        }

        System.out.println("Next Iteration after removing all elements");
        itr = list.iterator();
        while(itr.hasNext()){

            final String next = itr.next();
//            if(next.equals("c"))
            itr.remove();


            System.out.println(next);
        }
    }
}
