/*
Question:
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int n : map.keySet()) {
            pq.add(n);
            if (pq.size() > k) pq.poll();
        }
        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) ans[i] = pq.poll();
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
