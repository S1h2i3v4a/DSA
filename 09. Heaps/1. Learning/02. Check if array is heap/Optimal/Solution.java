/*
QUESTION:
Given an array `arr` of size `n`, check if the given array represents a Min-Heap.
Return true if it represents a Min-Heap, otherwise return false.

APPROACH:
- In a Min-Heap, every parent node at index `i` must be smaller than or equal to its left child `(2*i + 1)` and right child `(2*i + 2)`.
- We only need to check internal nodes from `i = 0` to `(n - 2) / 2`.
- If any child is smaller than parent, return false.

TIME COMPLEXITY: O(N) single pass through non-leaf nodes.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static boolean isMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] > arr[left]) return false;
            if (right < n && arr[i] > arr[right]) return false;
        }
        return true;
    }
}
