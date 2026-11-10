/*
QUESTION:
Given an array `arr` representing a Min-Heap, convert it into a Max-Heap in-place.

APPROACH:
- Building a Max-Heap from an existing array can be done using the standard `buildMaxHeap` method.
- Call `maxHeapify` starting from the last internal node `(n - 2) / 2` down to the root index `0`.
- Each call ensures the subtree rooted at `i` satisfies the Max-Heap property.

TIME COMPLEXITY: O(N) linear time to build heap.
SPACE COMPLEXITY: O(log N) recursion stack space for heapify.
*/

class Solution {
    private static void maxHeapify(int[] arr, int i, int n) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, n);
        }
    }

    public static void convertMinToMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            maxHeapify(arr, i, n);
        }
    }
}
