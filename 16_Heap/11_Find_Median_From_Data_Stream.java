/**
 * Problem Name: Find Median from Data Stream
 * Platform: LeetCode (LC 295)
 * Difficulty: Hard
 * 
 * Time Complexity:
 *   - addNum: O(log N)
 *   - findMedian: O(1)
 * Space Complexity: O(N) to store stream numbers
 * 
 * Approach:
 * Use two heaps:
 *   1. maxHeap: Max-Heap to store the smaller half of numbers.
 *   2. minHeap: Min-Heap to store the larger half of numbers.
 * 
 * Invariants:
 *   - maxHeap size is either equal to minHeap size or exactly 1 greater.
 *   - Every element in maxHeap is <= every element in minHeap.
 */

import java.util.*;

class Find_Median_From_Data_Stream {
    private PriorityQueue<Integer> maxHeap; // Lower half
    private PriorityQueue<Integer> minHeap; // Upper half

    public Find_Median_From_Data_Stream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        Find_Median_From_Data_Stream medianFinder = new Find_Median_From_Data_Stream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after [1, 2]: " + medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println("Median after [1, 2, 3]: " + medianFinder.findMedian()); // 2.0
    }
}
