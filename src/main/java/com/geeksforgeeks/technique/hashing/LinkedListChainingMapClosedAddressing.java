package com.geeksforgeeks.technique.hashing;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.IntStream;

public class LinkedListChainingMapClosedAddressing {
    public static void main(String[] args) {
        Map<Integer, String> map = new Map<>(7);

        IntStream.range(0, 100).forEach(i -> map.put(i, String.valueOf(i)));

        System.out.println(map.delete(99));
        System.out.println(map.search(100));
        System.out.println(map.search(99));
        System.out.println(map.search(98));
        System.out.println(map.get(98));
    }

    static class Map<K, V> {
        private int bucketSize;
        private LinkedList<Node<K, V>>[] bucket;

        Map(int bucketSize) {
            this.bucketSize = bucketSize;
            this.bucket = new LinkedList[bucketSize];
        }

        void put(K key, V val) {
            int idx = key.hashCode() % bucketSize;
            if (null == bucket[idx]) {
                bucket[idx] = new LinkedList<>();
            }

            final Node<K, V> node = new Node<>(key, val);
            if (bucket[idx].contains(node)) {
                for (int i = 0; i < bucket[idx].size(); i++) {
                    final Node<K, V> kvNode = bucket[idx].get(i);
                    if (kvNode.key.equals(key)) {
                        kvNode.value = val;
                        break;
                    }
                }
            } else {
                bucket[idx].add(node);
            }
        }

        boolean search(K key) {
            int idx = key.hashCode() % bucketSize;
            if (null == bucket[idx]) {
                bucket[idx] = new LinkedList<>();
            }
            for (int i = 0; i < bucket[idx].size(); i++) {
                final Node<K, V> kvNode = bucket[idx].get(i);
                if (kvNode.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        V get(K key){
            int idx = key.hashCode() % bucketSize;
            if (null == bucket[idx]) {
                return null;
            }

            for (int i = 0; i < bucket[idx].size(); i++) {
                final Node<K, V> kvNode = bucket[idx].get(i);
                if (kvNode.key.equals(key)) {
                    return kvNode.value;
                }
            }

            return null;
        }

        V delete(K key){
            int idx = key.hashCode() % bucketSize;
            if (null == bucket[idx]) {
                return null;
            }

            for (int i = 0; i < bucket[idx].size(); i++) {
                final Node<K, V> kvNode = bucket[idx].get(i);
                if (kvNode.key.equals(key)) {
                    bucket[idx].remove(kvNode);
                    return kvNode.value;
                }
            }

            return null;
        }

        static class Node<K, V> {
            private K key;
            private V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node<?, ?> node = (Node<?, ?>) o;
                return Objects.equals(key, node.key) && Objects.equals(value, node.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(key, value);
            }
        }
    }
}
