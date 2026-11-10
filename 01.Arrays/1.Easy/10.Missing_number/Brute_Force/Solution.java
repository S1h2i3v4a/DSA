/*
QUESTION:-
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example:
Input: nums = [3,0,1]
Output: 2
*/

/*
APPROACH:-
For every number from 0 to n, linearly scan the entire array to check if it exists. The one not found is returned.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            boolean found = false;
            for (int x : nums) {
                if (x == i) { found = true; break; }
            }
            if (!found) return i;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
