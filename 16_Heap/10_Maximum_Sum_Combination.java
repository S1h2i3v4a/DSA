/**
 * Problem Name: Maximum Sum Combination
 * Platform: GeeksforGeeks / InterviewBit
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log N + C log C)
 * Space Complexity: O(C) for Max-Heap and Visited Set
 * 
 * Approach:
 * 1. Sort both arrays A and B in ascending order.
 * 2. The maximum possible sum comes from the last elements (N-1, N-1).
 * 3. Push (A[N-1] + B[N-1], N-1, N-1) into a Max-Heap and mark pair (N-1, N-1) as visited in a Set.
 * 4. Pop the max element from heap C times. For each popped element at (i, j):
 *    - Add sum to the result list.
 *    - Push candidate pairs (i-1, j) and (i, j-1) to heap if valid and unvisited.
 */

import java.util.*;

class Maximum_Sum_Combination {
    private static class Element implements Comparable<Element> {
        int sum;
        int i;
        int j;

        Element(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(other.sum, this.sum); // Max-Heap
        }
    }

    public List<Integer> maxCombinations(int N, int K, int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        PriorityQueue<Element> maxHeap = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        int i = N - 1;
        int j = N - 1;
        maxHeap.offer(new Element(A[i] + B[j], i, j));
        visited.add(i + "," + j);

        List<Integer> result = new ArrayList<>();

        while (K > 0 && !maxHeap.isEmpty()) {
            Element top = maxHeap.poll();
            result.add(top.sum);
            K--;

            int currI = top.i;
            int currJ = top.j;

            // Candidate 1: (currI - 1, currJ)
            if (currI - 1 >= 0) {
                String key = (currI - 1) + "," + currJ;
                if (!visited.contains(key)) {
                    maxHeap.offer(new Element(A[currI - 1] + B[currJ], currI - 1, currJ));
                    visited.add(key);
                }
            }

            // Candidate 2: (currI, currJ - 1)
            if (currJ - 1 >= 0) {
                String key = currI + "," + (currJ - 1);
                if (!visited.contains(key)) {
                    maxHeap.offer(new Element(A[currI] + B[currJ - 1], currI, currJ - 1));
                    visited.add(key);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Maximum_Sum_Combination solver = new Maximum_Sum_Combination();
        int[] A = {3, 2, 4, 2};
        int[] B = {4, 3, 1, 2};
        int N = 4;
        int K = 3;
        System.out.println("Top " + K + " sum combinations: " + solver.maxCombinations(N, K, A, B));
        // A sorted: [2, 2, 3, 4]
        // B sorted: [1, 2, 3, 4]
        // Max sums: 4+4=8, 4+3=7, 3+4=7 => [8, 7, 7]
    }
}
