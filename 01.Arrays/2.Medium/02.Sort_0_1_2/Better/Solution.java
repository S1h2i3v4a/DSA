/*
QUESTION:-
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order 0, 1, and 2.

Example:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/

/*
APPROACH:-
Counting Sort: Count occurrences of 0, 1, and 2 in a first pass. In a second pass, overwrite the array with count0 zeros, count1 ones, and count2 twos.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void sortColors(int[] nums) {
        int c0 = 0, c1 = 0, c2 = 0;
        for (int x : nums) {
            if (x == 0) c0++;
            else if (x == 1) c1++;
            else c2++;
        }
        int idx = 0;
        for (int i = 0; i < c0; i++) nums[idx++] = 0;
        for (int i = 0; i < c1; i++) nums[idx++] = 1;
        for (int i = 0; i < c2; i++) nums[idx++] = 2;
    }
}

// TIME COMPLEXITY = O(2N)
// SPACE COMPLEXITY = O(1)
