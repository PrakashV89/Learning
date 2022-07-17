package com.java.arrays;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ArraysTest {
    public static void main(String[] args) {
        int[] intArr = new int[6];
        AtomicInteger index = new AtomicInteger(100);
        Arrays.setAll(intArr, operand -> index.incrementAndGet());

        Arrays.parallelSetAll(intArr, operand -> index.incrementAndGet());

        System.out.println(Arrays.toString(intArr));
    }
}
