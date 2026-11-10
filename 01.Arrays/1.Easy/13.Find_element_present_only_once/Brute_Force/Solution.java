/*
QUESTION:-
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Example:
Input: nums = [4,1,2,1,2]
Output: 4
*/

/*
APPROACH:-
For each element, run an inner loop over the entire array to count its frequency. Return the element with frequency 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) count++;
            }
            if (count == 1) return nums[i];
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
