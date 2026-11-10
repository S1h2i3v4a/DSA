/*
QUESTION:-
Given the sorted rotated array nums of unique elements, return the minimum element of this array.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
*/

/*
APPROACH:-
Linear scan: Traverse through the array and keep track of the minimum element encountered.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findMin(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) min = nums[i];
        }
        return min;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
