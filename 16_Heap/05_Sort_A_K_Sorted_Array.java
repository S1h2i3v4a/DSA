/**
 * Problem Name: Sort a Nearly Sorted / K-Sorted Array
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log K) where N is array length and K is displacement.
 * Space Complexity: O(K) for PriorityQueue.
 * 
 * Approach:
 * Min-Heap Window of Size K+1.
 * Since every element is at most K positions away from its target sorted index:
 * 1. Insert initial `K + 1` elements into Min PriorityQueue.
 * 2. Iterate `i` from `K + 1` to `N - 1`: poll the smallest element from heap to `arr[index++]`, and offer `arr[i]`.
 * 3. Empty remaining elements from heap into array.
 */

import java.util.Arrays;
import java.util.PriorityQueue;

class Sort_A_K_Sorted_Array {

    public static void nearlySorted(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add first k + 1 elements
        for (int i = 0; i <= k && i < n; i++) {
            minHeap.offer(arr[i]);
        }

        int index = 0;
        for (int i = k + 1; i < n; i++) {
            arr[index++] = minHeap.poll();
            minHeap.offer(arr[i]);
        }

        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;
        System.out.println("Nearly Sorted Array: " + Arrays.toString(arr));
        nearlySorted(arr, k);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}
