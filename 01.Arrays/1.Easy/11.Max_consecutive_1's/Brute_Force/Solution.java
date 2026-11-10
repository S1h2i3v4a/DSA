/*
QUESTION:-
Given a binary array nums, return the maximum number of consecutive 1's in the array.

Example:
Input: nums = [1,1,0,1,1,1]
Output: 3
*/

/*
APPROACH:-
For each starting index i, count consecutive 1s until a 0 or array boundary is reached. Maintain global maximum.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1) count++;
                else break;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
