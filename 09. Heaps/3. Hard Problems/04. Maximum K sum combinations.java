/*
Question:
Given two integer arrays A and B of size N each. A sum combination is made by adding one element from array A and another element from array B. Return the maximum K valid distinct sum combinations from all the possible sum combinations. Output array must be sorted in non-increasing order.

Example:
Input:
N = 2
K = 2
A[] = {3, 2}
B[] = {1, 4}
Output: {7, 6}
Explanation: 
7 -> (A: 3) + (B: 4)
6 -> (A: 2) + (B: 4)
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
