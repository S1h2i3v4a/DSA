/*
QUESTION:-
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements. Must do this in-place.

Example:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
*/

/*
APPROACH:-
Two-pointer approach: Find the first 0 at index j. Iterate i from j+1 to n-1; whenever nums[i] != 0, swap nums[i] and nums[j], then increment j.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void moveZeroes(int[] nums) {
        int j = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }
        if (j == -1) return;
        for (int i = j + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
