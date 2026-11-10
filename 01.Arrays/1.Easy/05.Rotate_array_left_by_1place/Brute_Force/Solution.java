/*
QUESTION:-
Given an array nums of size n, rotate the array to the left by one position in-place.

Example:
Input: nums = [1,2,3,4,5], n = 5
Output: [2,3,4,5,1]
*/

/*
APPROACH:-
Use a temporary array of size n. Copy nums[1..n-1] to temp[0..n-2] and nums[0] to temp[n-1], then copy temp back into nums.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[] nums, int n) {
        int[] temp = new int[n];
        for (int i = 1; i < n; i++) temp[i - 1] = nums[i];
        temp[n - 1] = nums[0];
        for (int i = 0; i < n; i++) nums[i] = temp[i];
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
