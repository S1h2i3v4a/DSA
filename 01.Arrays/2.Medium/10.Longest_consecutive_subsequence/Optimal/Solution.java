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
Insert all elements into a HashSet. For each element x, check if (x - 1) is in set. If not, x is the start of a streak: while (x + 1) is in set, x++, streak++. Track max streak.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int maxLen = 0;
        for (int x : set) {
            if (!set.contains(x - 1)) {
                int curr = x;
                int count = 1;
                while (set.contains(curr + 1)) {
                    curr++;
                    count++;
                }
                maxLen = Math.max(maxLen, count);
            }
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
