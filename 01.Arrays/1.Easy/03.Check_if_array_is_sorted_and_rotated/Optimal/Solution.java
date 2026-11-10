/*
QUESTION:-
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

Example:
Input: nums = [3,4,5,1,2]
Output: true
*/

/*
APPROACH:-
Count the number of inversion pairs where nums[i] > nums[(i + 1) % n]. A sorted and rotated array can have at most one such drop.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean check(int[] nums) {
        int count = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) count++;
        }
        return count <= 1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
