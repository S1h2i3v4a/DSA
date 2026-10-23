/**
 * Problem Name: Find Median from Data Stream
 * Platform: LeetCode (LC 295) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity:
 *   - addNum(): O(log N)
 *   - findMedian(): O(1)
 * Space Complexity: O(N) to store all incoming stream elements
 *
 * Approach:
 *   - Two-Heap Pattern:
 *     1. A Max-Heap (`maxHeap`) storing the smaller half of the numbers.
 *     2. A Min-Heap (`minHeap`) storing the larger half of the numbers.
 *   - Invariants:
 *     1. maxHeap.peek() <= minHeap.peek() (all elements in smaller half <= all elements in larger half).
 *     2. Size balance: maxHeap.size() is either equal to minHeap.size() or larger by exactly 1.
 *   - Operations:
 *     - addNum(num):
 *       Push to maxHeap, then transfer top of maxHeap to minHeap to maintain order.
 *       If minHeap.size() > maxHeap.size(), balance by moving top of minHeap to maxHeap.
 *     - findMedian():
 *       If maxHeap has more elements, median is maxHeap.peek().
 *       If equal sizes, median is (maxHeap.peek() + minHeap.peek()) / 2.0.
 */

import java.util.Collections;
import java.util.PriorityQueue;

class FindMedianFromDataStream {

    static class MedianFinder {
        private final PriorityQueue<Integer> maxHeap; // Smaller half
        private final PriorityQueue<Integer> minHeap; // Larger half

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());

            // Balance size invariant: maxHeap size >= minHeap size
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median after [1, 2]: " + mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println("Median after [1, 2, 3]: " + mf.findMedian()); // 2.0
        mf.addNum(5);
        System.out.println("Median after [1, 2, 3, 5]: " + mf.findMedian()); // 2.5
    }
}
