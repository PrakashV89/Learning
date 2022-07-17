package com.geeksforgeeks.array.sort.comparison;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 5, 6, 4, 2, 1};

        System.out.println("Performing Descending Sort..");
        new Descending().heapSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("Performing Ascending Sort..");
        new Ascending().heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static abstract class AbstractHeapSort {
        void heapSort(int[] arr) {
            heapSort(arr, arr.length);
        }

        private void heapSort(int[] arr, int n) {
            for (int i = n; i > 0; i--) {
                buildHeap(arr, i);
                swap(arr, 0, i - 1);
            }

        }

        protected void swap(int[] arr, int idx, int swapIndex) {
            int tmp = arr[idx];
            arr[idx] = arr[swapIndex];
            arr[swapIndex] = tmp;
        }


        void buildHeap(int[] arr, int n) {
            for (int i = (n - 2) / 2; i >= 0; i--) {
                heapify(arr, i, n);
            }
        }

        abstract void heapify(int[] arr, int i, int n);
    }

    static class Ascending extends AbstractHeapSort {
        @Override
        void heapify(int[] arr, int i, int n) {
            if (i >= n) {
                return;
            }

            int firstChildIdx = (2 * i) + 1;
            int secondChildIdx = firstChildIdx + 1;

            int maxVal = arr[i];
            int swapIndex = -1;
            if (firstChildIdx < n) {
                if (arr[firstChildIdx] > maxVal) {
                    swapIndex = firstChildIdx;
                    maxVal = arr[firstChildIdx];
                }
            }

            if (secondChildIdx < n) {
                if (arr[secondChildIdx] > maxVal) {
                    swapIndex = secondChildIdx;
                }
            }

            if (swapIndex != -1) {
                swap(arr, i, swapIndex);
                heapify(arr, swapIndex, n);
            }
        }


    }

    static class Descending extends AbstractHeapSort {

        void heapify(int[] arr, int i, int n) {
            if (i >= n) {
                return;
            }

            int firstChildIdx = (2 * i) + 1;
            int secondChildIdx = firstChildIdx + 1;

            int minVal = arr[i];
            int swapIndex = -1;
            if (firstChildIdx < n) {
                if (arr[firstChildIdx] < minVal) {
                    swapIndex = firstChildIdx;
                    minVal = arr[firstChildIdx];
                }
            }

            if (secondChildIdx < n) {
                if (arr[secondChildIdx] < minVal) {
                    swapIndex = secondChildIdx;
                }
            }

            if (swapIndex != -1) {
                swap(arr, i, swapIndex);
                heapify(arr, swapIndex, n);
            }
        }
    }
}
