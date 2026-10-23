/*
QUESTION:
Given K sorted arrays arranged in the form of a matrix of size K*K, the task is to merge them into one sorted array.

APPROACH:
To merge K sorted arrays, we can use a min-heap (priority queue) to store the smallest elements from each array.
1. Create a min-heap of size K to store the current smallest elements from each array.
2. Initialize the min-heap with the first element from each array.
3. While the min-heap is not empty, extract the smallest element from the min-heap, add it to the merged array, and replace it with the next element from the same array.
4. Repeat step 3 until all elements from all arrays are processed.

The mergeKArrays function implements this approach. It uses a min-heap (priority queue) to merge K sorted arrays into one sorted array.

TIME COMPLEXITY:
The time complexity of the mergeKArrays function is O(K^2 log K), where K is the size of each array. Inserting elements into the min-heap and extracting the minimum element take O(log K) time, and we do this for K^2 elements. The overall time complexity is dominated by the heap operations.

SPACE COMPLEXITY:
The space complexity is O(K) as we need to store K elements in the min-heap.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static class Node {
        int val, row, col;
        Node(int v, int r, int c) { val = v; row = r; col = c; }
    }
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (int i = 0; i < K; i++) pq.add(new Node(arr[i][0], i, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            ans.add(curr.val);
            if (curr.col + 1 < arr[curr.row].length) {
                pq.add(new Node(arr[curr.row][curr.col + 1], curr.row, curr.col + 1));
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
