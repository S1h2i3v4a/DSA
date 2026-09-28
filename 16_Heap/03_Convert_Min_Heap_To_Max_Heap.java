/**
 * Problem Name: Convert Min Heap to Max Heap
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) linear time heap build.
 * Space Complexity: O(1) auxiliary space (in-place modification).
 * 
 * Approach:
 * Bottom-Up Max Heapify.
 * Treat the input array as an unsorted complete binary tree.
 * Call `maxHeapify(arr, i, n)` starting from the last non-leaf node `(n - 2) / 2` down to `0`.
 * `maxHeapify` compares `arr[i]` with its left (`2*i + 1`) and right (`2*i + 2`) children, swaps with the largest child, and recursively heapifies down.
 */

import java.util.Arrays;

class Convert_Min_Heap_To_Max_Heap {

    public static void convertMinToMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            maxHeapify(arr, i, n);
        }
    }

    private static void maxHeapify(int[] arr, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            maxHeapify(arr, largest, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 9, 6, 8, 20, 10, 12, 18, 9};
        System.out.println("Min Heap: " + Arrays.toString(arr));
        convertMinToMaxHeap(arr);
        System.out.println("Converted Max Heap: " + Arrays.toString(arr));
    }
}
