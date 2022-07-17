package com.geeksforgeeks.array.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
//        new QSWithNaivePartition().sort(new int[]{1, 2, 7, 8, 3, 4, 7, 9});
//        new QSWithLomutoPartition().sort(new int[]{1, 2, 7, 8, 3, 4, 7, 9});
        new QSWithHoarePartition().sort(new int[]{1, 2, 7, 8, 3, 4, 7, 9});
    }

    static class QSWithHoarePartition extends AbstractQuickSort{

        @Override
        int partition(int[] arr, int low, int high) {
            int p = low;

            int random = new Random().nextInt((high-low) + 1) + low;
            swap(arr, random, p);

            int i = low - 1;
            int j = high;
            int pivot = arr[p];
            while(true){
                do {
                    i++;
                }
                while (arr[i] < pivot);

                do {
                    j--;
                } while (arr[j] > pivot);

                if(i >= j){
                    return j;
                }
                swap(arr, i, j);
            }
        }

        @Override
        public int getPivot(int pivot) {
            return pivot;
        }
    }


    static class QSWithNaivePartition extends  AbstractQuickSort{


        public int partition(int[] arr, int low, int high) {
            int p = low;//can be high or any random number
            int tmp[] = new int[high - low + 1];
            int idx = 0;
            for (int i = low; i <= high; i++) {
                if (arr[i] < arr[p]) {
                    tmp[idx++] = arr[i];
                }
            }

            for (int i = low; i <= high; i++) {
                if (arr[i] == arr[p]) {
                    tmp[idx++] = arr[i];
                }
            }

            int res = low + idx-1;

            for (int i = low; i <= high; i++) {
                if (arr[i] > arr[p]) {
                    tmp[idx++] = arr[i];
                }
            }

            for (int i = low; i <= high; i++) {
                arr[i] = tmp[i - low];
            }

            return res;
        }


    }

    static class QSWithLomutoPartition extends  AbstractQuickSort{

        private Random random = new Random();

        public int partition(int[] arr, int low, int high) {
            int p = high;

            final int random = this.random.nextInt((high - low) + 1) + low;
            swap(arr, random, high);

            int j = low;
            int i = j - 1;
            while(j < high){
                if(arr[j] < arr[p]){
                    i++;
                    swap(arr, i, j);
                }
                j++;
            }
            swap(arr, ++i, high);

            return i;
        }


    }

    abstract static  class AbstractQuickSort{
        public void sort(int[] arr) {
            System.out.println(Arrays.toString(arr));
            sort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));
        }

        public void sort(int[] arr, int low, int high) {
            if (low < high) {
                int pivot = partition(arr, low, high);
                sort(arr, low, getPivot(pivot));
                sort(arr, pivot+1, high);
            }
            return;
        }

        public int getPivot(int pivot) {
            return pivot - 1;
        }

        abstract int partition(int[] arr, int low, int high);

        void swap(int[] arr, int i, int j){
            System.out.println("Swapping " + arr[i] + " and " + arr[j]);
            if(arr[i] == arr[j])
                return;
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
            System.out.println(Arrays.toString(arr));
        }
    }
}
