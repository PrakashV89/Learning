package com.geeksforgeeks.technique.hashing.addressing.open;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DoubleHashingHandleWithMinus1AndMinus2AsKeys {

    public static void main(String[] args) {
        HashTable ht = new HashTable(7);
        ht.insert(49);
        ht.print();
        ht.insert(56);
        ht.print();
        ht.insert(72);
        ht.print();
        if (ht.search(56)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        ht.erase(56);
        ht.print();
        if (ht.search(56)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.println(IntStream.range(0, 100).mapToObj(i -> {
                    boolean inserted = ht.insert(i);
                    if(inserted)
                        ht.print();
                    return inserted;
                })
                .filter(i -> !i)
                .count());
//        ht.print();
        final ArrayList<Object> collect = IntStream.range(0, 100).mapToObj(ht::search)
                .filter(i -> !i)
                .distinct().collect(Collectors.toCollection(ArrayList::new)
                );
        System.out.println(IntStream.range(0, 100).mapToObj(ht::search)
                .filter(i -> !i).count());
        IntStream.range(0, 100)
                .filter(i -> ht.search(i)).forEach(System.out::println);
    }

    static class HashTable {
        private int bucketSize;
        private Integer[] bucket;

        HashTable(int bucketSize) {
            this.bucketSize = bucketSize;
            this.bucket = new Integer[bucketSize];
        }

        boolean insert(int elem) {
            int i = 0;
            int idx = hash(elem, i);
            int startIdx = idx;
            while (null != bucket[idx] && Integer.MAX_VALUE + 1 != bucket[idx] && bucket[idx] != elem) {
                idx = hash(elem, ++i);
                if (startIdx == idx) {
                    return false;
                }
            }
            if(null !=bucket[idx] && bucket[idx] == elem){
                return false;
            }
            bucket[idx] = elem;
            return true;
        }

        boolean search(int elem) {
            int i = 0;
            int idx = hash(elem, ++i);
            int startIdx = idx;
            while (bucket[idx] != null && elem != bucket[idx]) {
                if(bucket[idx] == null){
                    return  false;
                }
                idx = hash(elem, ++i);
                if (startIdx == idx) {
                    return false;
                }
            }
            return true;
        }

        int erase(int elem) {
            int i = 0;
            int idx = hash(elem, i);
            int startIdx = idx;
            while (elem != bucket[idx]) {
                if(bucket[idx] == null){
                    return  -1;
                }
                idx = hash(elem, ++i);
                if (startIdx == idx) {
                    return -1;
                }
            }
            bucket[idx] = Integer.MAX_VALUE + 1;
            return idx;
        }

        public int hash(int elem, int i) {
            return (elem + i * hash2(elem)) % bucketSize;
        }

        public int hash2(int elem) {
            return (bucketSize - 1) - ((elem) % (bucketSize - 1));
        }

        public void print() {
            for (int i = 0; i < bucketSize; i++) {
                System.out.print(bucket[i] + " ");
            }
            System.out.println("");
        }
    }
}
