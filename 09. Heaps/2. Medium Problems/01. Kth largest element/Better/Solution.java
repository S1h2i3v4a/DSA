/*
QUESTION:
Given an integer array `nums` and an integer `k`, return the `k`th largest element in the array.

APPROACH:
- Use a Min-Heap (PriorityQueue) of size `k`.
- Iterate through each number in `nums`:
  - Add number to heap.
  - If heap size exceeds `k`, poll the smallest element.
- After processing all numbers, the root of the min-heap is the `k`th largest element.

TIME COMPLEXITY: O(N log K) maintaining heap of size k.
SPACE COMPLEXITY: O(K) for min-heap.
*/

import java.util.PriorityQueue;

class Solution {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
