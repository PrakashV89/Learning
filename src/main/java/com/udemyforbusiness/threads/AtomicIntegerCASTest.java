package com.udemyforbusiness.threads;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicIntegerCASTest {
    Node n1 = new Node(1, 5);
    Node n2 = new Node(17, 11);
    Node n3 = new Node(1, 5);
    Node n4 = n1;

    AtomicReference<Node> atomicN1 = new AtomicReference<>(n1);

    public class Node {
        private final int key;
        private final int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        AtomicIntegerCASTest atomicIntegerCASTest = new AtomicIntegerCASTest();
        System.out
                .println(atomicIntegerCASTest.atomicN1.compareAndSet(atomicIntegerCASTest.n4, atomicIntegerCASTest.n3));
        System.out
                .println(atomicIntegerCASTest.atomicN1.compareAndSet(atomicIntegerCASTest.n1, atomicIntegerCASTest.n3));
        System.out
                .println(atomicIntegerCASTest.atomicN1.compareAndSet(atomicIntegerCASTest.n2, atomicIntegerCASTest.n3));
    }
}
