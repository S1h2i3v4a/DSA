/*
QUESTION:-
Given an integer array of size n, find all elements that appear more than floor(n/3) times.

Example:
Input: nums = [3,2,3]
Output: [3]
*/

/*
APPROACH:-
Use a HashMap to count frequencies of each element. Check and collect all elements with frequency > n / 3.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int threshold = n / 3;
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > threshold) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
