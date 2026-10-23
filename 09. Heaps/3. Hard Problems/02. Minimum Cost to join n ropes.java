/*
Question:
There are given N ropes of different lengths, we need to connect these ropes into one rope. The cost to connect two ropes is equal to the sum of their lengths. The task is to connect the ropes with the minimum cost. Given an array arr[] containing the lengths of the ropes.

Example:
Input: arr[] = {4, 3, 2, 6}
Output: 29
Explanation: The minimum cost is obtained by connecting ropes in the following order:
1. Connect ropes of length 2 and 3, cost = 2 + 3 = 5, resulting array = {4, 5, 6}
2. Connect ropes of length 4 and 5, cost = 4 + 5 = 9, resulting array = {9, 6}
3. Connect ropes of length 6 and 9, cost = 6 + 9 = 15, resulting array = {15}
Total cost = 5 + 9 + 15 = 29

Approach:
1. Use a priority queue (min heap) to store the lengths of the ropes.
2. Push all the rope lengths into the priority queue.
3. While the priority queue has more than one element:
   - Extract the two smallest ropes from the priority queue.
   - Add their lengths together to get the cost.
   - Push the sum back into the priority queue.
   - Update the total cost.
4. Return the total cost as the minimum cost of connecting the ropes.

Complexity Analysis:
- Time complexity: O(n log n), where n is the number of ropes. Inserting and extracting elements from the priority queue take O(log n) time, and the loop runs for (n-1) iterations.
- Space complexity: O(n) to store the rope lengths in the priority queue.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    long minCost(long arr[], int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (long x : arr) pq.add(x);
        long cost = 0;
        while (pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            cost += (first + second);
            pq.add(first + second);
        }
        return cost;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
