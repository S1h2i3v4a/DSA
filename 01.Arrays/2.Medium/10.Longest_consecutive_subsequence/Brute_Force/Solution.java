/*
QUESTION:-
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence. Must run in O(n) time.

Example:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/

/*
APPROACH:-
For each element x in nums, perform a linear search to check if x+1 exists, then x+2, etc., keeping track of consecutive length.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int maxLen = 1;
        for (int x : nums) {
            int curr = x;
            int count = 1;
            while (linearSearch(nums, curr + 1)) {
                curr++;
                count++;
            }
            maxLen = Math.max(maxLen, count);
        }
        return maxLen;
    }
    private boolean linearSearch(int[] nums, int target) {
        for (int x : nums) if (x == target) return true;
        return false;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
