/*
QUESTION:
Given an array `arr[]` and an integer `k`, find the `k`th smallest element in the given array.

APPROACH:
- Use a Max-Heap of size `k`.
- Traverse the elements from index `l` to `r`:
  - Add element to max-heap.
  - If heap size exceeds `k`, remove largest.
- Top of the max-heap is the `k`th smallest element.

TIME COMPLEXITY: O(N log K) where N is number of elements in range [l, r].
SPACE COMPLEXITY: O(K) for max-heap.
*/

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = l; i <= r; i++) {
            maxHeap.offer(arr[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }
}
