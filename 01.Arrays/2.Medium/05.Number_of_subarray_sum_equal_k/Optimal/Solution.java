/*
QUESTION:-
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

Example:
Input: nums = [1,1,1], k = 2
Output: 2
*/

/*
APPROACH:-
Prefix Sum with HashMap: Maintain prefix sum. If (prefixSum - k) exists in map, add its frequency to count. Insert/update current prefixSum in map.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int x : nums) {
            sum += x;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
