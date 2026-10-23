/*
QUESTION:
Given an array of integers nums and a positive integer k, check whether it is possible to divide this array into sets of k consecutive numbers.

Return true if it is possible. Otherwise, return false.

Example:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int x : nums) count.put(x, count.getOrDefault(x, 0) + 1);
        for (int key : count.keySet()) {
            int c = count.get(key);
            if (c > 0) {
                for (int i = 0; i < k; i++) {
                    if (count.getOrDefault(key + i, 0) < c) return false;
                    count.put(key + i, count.get(key + i) - c);
                }
            }
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
