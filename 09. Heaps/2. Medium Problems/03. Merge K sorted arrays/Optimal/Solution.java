/*
QUESTION:
Given `K` sorted arrays of size `K` each, merge them into a single sorted array.

APPROACH:
- Use a Min-Heap storing triples `(val, arrayIndex, elementIndex)`.
- Push the first element of each of the `K` arrays into the heap.
- While heap is non-empty:
  - Pop the smallest element and add its value to result.
  - If the array from which it was picked has a next element, push the next element into the heap.

TIME COMPLEXITY: O(N log K) where N = K * K total elements.
SPACE COMPLEXITY: O(K) for min-heap of size K.
*/

import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    private static class Node implements Comparable<Node> {
        int val, row, col;
        Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
        public int compareTo(Node other) {
            return Integer.compare(this.val, other.val);
        }
    }

    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        for (int i = 0; i < K; i++) {
            if (arr[i].length > 0) {
                minHeap.offer(new Node(arr[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            result.add(curr.val);

            if (curr.col + 1 < arr[curr.row].length) {
                minHeap.offer(new Node(arr[curr.row][curr.col + 1], curr.row, curr.col + 1));
            }
        }
        return result;
    }
}
