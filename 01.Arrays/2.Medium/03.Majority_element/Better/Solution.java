/*
QUESTION:-
Given an array nums of size n, return the majority element. The majority element is the element that appears more than floor(n / 2) times.

Example:
Input: nums = [3,2,3]
Output: 3
*/

/*
APPROACH:-
Use a HashMap to track the frequency of each element. Return the element as soon as its frequency exceeds n / 2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int x : nums) {
            int count = map.getOrDefault(x, 0) + 1;
            if (count > n / 2) return x;
            map.put(x, count);
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
