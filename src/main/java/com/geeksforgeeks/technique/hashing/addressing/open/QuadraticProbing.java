package com.geeksforgeeks.technique.hashing.addressing.open;

public class QuadraticProbing {

    public static void main(String[] args) {
        HashTable ht = new HashTable(7);
        ht.insert(49);
        ht.insert(56);
        ht.insert(72);
        if(ht.search(56)){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
        ht.erase(56);
        if(ht.search(56)){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }

    static class HashTable{
        private int bucketSize;
        private int[] bucket;

        HashTable(int bucketSize){
            this.bucketSize = bucketSize;
            this.bucket = new int[bucketSize];

            for (int i = 0; i < bucketSize; i++) {
                bucket[i] = -1;
            }
        }

        void insert(int elem){
            int i = 0;
            int idx = hash(elem, i);
            int startIdx = idx;
            while(-1 != bucket[idx] && -2 != bucket[idx] && bucket[idx] != elem){
                idx = hash(elem, ++i);
                if(startIdx == idx){
                    return;
                }
            }
            if(bucket[idx] != elem) {
                bucket[idx] = elem;
            }
        }

        boolean search(int elem){
            int i = 0;
            int idx = hash(elem, ++i);
            int startIdx = idx;
            while(elem != bucket[idx]){
                idx = hash(elem, i);
                if(startIdx == idx){
                    return false;
                }
            }
            return true;
        }

        int erase(int elem){
            int i = 0;
            int idx = hash(elem, i);
            int startIdx = idx;
            while(elem != bucket[idx]){
                if(bucket[idx] == -1){
                    return -1;
                }

                idx = hash(elem, ++i);
                if(startIdx == idx){
                    return -1;
                }
            }
            bucket[idx] = -2;
            return idx;
        }

        public int hash(int elem, int i){
            return (elem + i^2) % bucketSize;
        }
    }
}
