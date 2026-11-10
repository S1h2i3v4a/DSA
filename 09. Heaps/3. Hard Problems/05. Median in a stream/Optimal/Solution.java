/*
QUESTION:
Find median in a stream in O(log N) per `addNum` and O(1) for `findMedian` using two heaps.

APPROACH:
- Use two heaps:
  - `left` (Max-Heap) storing the smaller half of numbers.
  - `right` (Min-Heap) storing the larger half of numbers.
- Invariant: `left.size() == right.size()` or `left.size() == right.size() + 1`.
- On `addNum(num)`:
  - Add to `left` if `left` empty or `num <= left.peek()`, else add to `right`.
  - Rebalance if `left.size() > right.size() + 1` (poll from left to right) or `right.size() > left.size()` (poll from right to left).
- On `findMedian()`:
  - If `left.size() > right.size()`, return `left.peek()`.
  - Else return `(left.peek() + right.peek()) / 2.0`.

TIME COMPLEXITY:
- addNum: O(log N).
- findMedian: O(1).
SPACE COMPLEXITY: O(N) across the two heaps.
*/

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    public Solution() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
        } else {
            right.offer(num);
        }

        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
        } else if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        } else {
            return (left.peek() + right.peek()) / 2.0;
        }
    }
}
