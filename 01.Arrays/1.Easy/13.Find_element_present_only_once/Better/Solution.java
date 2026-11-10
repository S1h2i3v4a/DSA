/*
QUESTION:-
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Example:
Input: nums = [4,1,2,1,2]
Output: 4
*/

/*
APPROACH:-
Use a HashMap to store frequencies of all elements. Then iterate through map entries and return the key with value 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) return e.getKey();
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
