/*
QUESTION:
There are given N ropes of different lengths, we need to connect these ropes into one rope.
The cost to connect two ropes is equal to the sum of their lengths. Find the minimum cost to connect all ropes.

APPROACH:
- Greedily pick the two smallest ropes at each step using a Min-Heap (PriorityQueue).
- Connect them: add their combined length to total cost, and push the combined length back into the heap.
- Repeat until only one rope remains.

TIME COMPLEXITY: O(N log N) inserting and polling from min-heap.
SPACE COMPLEXITY: O(N) for priority queue.
*/

import java.util.PriorityQueue;

class Solution {
    public long minCost(long arr[], int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(arr[i]);
        }

        long totalCost = 0;
        while (pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            long cost = first + second;
            totalCost += cost;
            pq.offer(cost);
        }
        return totalCost;
    }
}
