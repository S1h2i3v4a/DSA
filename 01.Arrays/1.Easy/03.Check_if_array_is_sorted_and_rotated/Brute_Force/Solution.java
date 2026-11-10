/*
QUESTION:-
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

Example:
Input: nums = [3,4,5,1,2]
Output: true
*/

/*
APPROACH:-
Test all possible rotation offsets k from 0 to n-1. Check if the array rotated by k offsets is sorted.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        for (int k = 0; k < n; k++) {
            boolean isSorted = true;
            for (int i = 0; i < n - 1; i++) {
                if (nums[(i + k) % n] > nums[(i + k + 1) % n]) {
                    isSorted = false;
                    break;
                }
            }
            if (isSorted) return true;
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
