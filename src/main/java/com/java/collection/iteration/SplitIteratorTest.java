package com.java.collection.iteration;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;

public class SplitIteratorTest {
    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c", "d");
        Spliterator<String> splitItr = list.spliterator();
        List<Spliterator<String>> spliterators = new ArrayList<>();
        while(true){
            Spliterator<String> splitted = splitItr.trySplit();
            if(splitted != null){
                spliterators.add(splitted);
                break;
            }
            else{
                break;
            }
        }
        spliterators.add(splitItr);

        spliterators.forEach(spliterator -> {System.out.println("$$$$$$$$$$");spliterator.forEachRemaining(System.out::println);});
        System.out.println("Starting remaining iterator...");
        splitItr.forEachRemaining(System.out::println);


        System.out.println("Starting new Split Iteration with tryAdvanced(action) and print last & first 2 elements");
        final Spliterator<String> spliterator1 = list.spliterator();
        final Spliterator<String> spliterator = spliterator1.trySplit();
        while(spliterator1.tryAdvance(System.out::println));
        while(spliterator.tryAdvance(System.out::println));
    }
}
