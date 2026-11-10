/*
QUESTION:
Find the `k`th largest element in a stream in O(log K) per query using a Min-Heap.

APPROACH:
- Maintain a Min-Heap of size `k`.
- Initialize heap with the `k` largest elements of `nums`.
- When `add(val)` is called:
  - Add `val` to min-heap.
  - If heap size exceeds `k`, poll the minimum.
  - The top of min-heap (`pq.peek()`) is always the `k`th largest element.

TIME COMPLEXITY: O(log K) per `add` operation.
SPACE COMPLEXITY: O(K) for min-heap of size k.
*/

import java.util.PriorityQueue;

class Solution {
    private int k;
    private PriorityQueue<Integer> minHeap;

    public Solution(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
