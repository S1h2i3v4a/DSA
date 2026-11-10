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
Sort the array. Iterate through sorted array: if nums[i] == nums[i-1] + 1, count++; if nums[i] != nums[i-1], reset count = 1. Update maxLen.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int maxLen = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] == nums[i - 1] + 1) {
                count++;
            } else {
                count = 1;
            }
            maxLen = Math.max(maxLen, count);
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(1)
