package com.geeksforgeeks.technique.hashing.addressing.closed;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class LinkedListChainingClosedAddressing {

    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(7);

        IntStream.range(0, 50).forEach(hashTable::insert);

        hashTable.remove(49);
        System.out.println(hashTable.search(48));

    }


    static class HashTable<E> {
        LinkedList<E>[] bucket;

        HashTable(int bucketSize) {
            this.bucket = new LinkedList[bucketSize];
        }

        void insert(E e) {
            int idx = hash(e);
            if (null == bucket[idx]) {
                bucket[idx] = new LinkedList<>();
                bucket[idx].add(e);
            } else {
                if (bucket[idx].contains(e)) {
                    for (int i = 0; i < bucket[idx].size(); i++) {
                        if (bucket[idx].get(i) == e) {
                            return;
                        }
                    }
                } else {
                    bucket[idx].add(e);
                }
            }
        }

        void remove(E e) {
            int idx = hash(e);
            bucket[idx].remove(e);
        }

        boolean search(E e) {
            int idx = hash(e);
//            for (int i = 0; i < bucket[idx].size(); i++) {
//                if(bucket[idx].get(i) == e){
//                    return true;
//                }
//            }

            return bucket[idx].contains(e);
        }


        int hash(E e) {
            return e.hashCode() % bucket.length;
        }
    }
}
