/*
QUESTION:
Return the `k` most frequent elements using a Min-Heap.

APPROACH:
- Count frequencies using a HashMap.
- Maintain a Min-Heap of size `k` ordered by frequency.
- For each unique element, add to heap; if heap size > k, poll the least frequent.
- Top `k` frequent elements remain in the heap.

TIME COMPLEXITY: O(D log K) where D is unique elements.
SPACE COMPLEXITY: O(D + K) for map and heap.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int key : map.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}
