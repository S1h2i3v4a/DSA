/*
QUESTION:-
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example:
Input: nums = [3,0,1]
Output: 2
*/

/*
APPROACH:-
Use a boolean array of size n+1 to mark visited numbers. Then traverse the boolean array to return the index marked false.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean[] hash = new boolean[n + 1];
        for (int x : nums) hash[x] = true;
        for (int i = 0; i <= n; i++) {
            if (!hash[i]) return i;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
