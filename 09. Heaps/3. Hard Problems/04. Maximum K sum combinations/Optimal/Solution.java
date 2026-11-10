/*
QUESTION:
Return the maximum `K` sum combinations from `A` and `B` in O(N log N + K log K).

APPROACH:
- Sort both `A` and `B` in ascending order.
- The largest possible sum is `A[N-1] + B[N-1]`.
- Use a Max-Heap of tuples `(sum, i, j)` and a HashSet of visited index pairs `(i, j)`.
- Push `(A[N-1] + B[N-1], N-1, N-1)` into max-heap.
- For `K` iterations:
  - Pop max sum and add to result.
  - Insert adjacent pairs `(i - 1, j)` and `(i, j - 1)` if not visited.

TIME COMPLEXITY: O(N log N + K log K).
SPACE COMPLEXITY: O(K) for max-heap and visited set.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    private static class Node implements Comparable<Node> {
        int sum, i, j;
        Node(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
        public int compareTo(Node other) {
            return Integer.compare(other.sum, this.sum); // Max-Heap
        }
    }

    public List<Integer> maxCombinations(int N, int K, int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        PriorityQueue<Node> maxHeap = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        int i = N - 1, j = N - 1;
        maxHeap.offer(new Node(A[i] + B[j], i, j));
        visited.add(i + "," + j);

        while (K > 0 && !maxHeap.isEmpty()) {
            Node curr = maxHeap.poll();
            result.add(curr.sum);
            K--;

            int r = curr.i;
            int c = curr.j;

            if (r - 1 >= 0 && !visited.contains((r - 1) + "," + c)) {
                maxHeap.offer(new Node(A[r - 1] + B[c], r - 1, c));
                visited.add((r - 1) + "," + c);
            }
            if (c - 1 >= 0 && !visited.contains(r + "," + (c - 1))) {
                maxHeap.offer(new Node(A[r] + B[c - 1], r, c - 1));
                visited.add(r + "," + (c - 1));
            }
        }
        return result;
    }
}
