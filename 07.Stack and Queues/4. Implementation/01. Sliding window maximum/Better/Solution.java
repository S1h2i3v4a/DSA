/*
QUESTION:
You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right.
Return the max sliding window.

APPROACH:
- Use a Max-Heap (PriorityQueue) storing pairs `(val, index)`.
- For each element, add it to the heap.
- Remove elements from heap top whose indices fall outside the current window `[i - k + 1, i]`.
- Heap top is the window maximum.

TIME COMPLEXITY: O(N log N) using a priority queue.
SPACE COMPLEXITY: O(N) for elements stored in heap.
*/

import java.util.PriorityQueue;

class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];
        int[] result = new int[n - k + 1];

        // Max heap storing int[] {val, index}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nums[i], i});

            if (i >= k - 1) {
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                result[i - k + 1] = pq.peek()[0];
            }
        }
        return result;
    }
}
