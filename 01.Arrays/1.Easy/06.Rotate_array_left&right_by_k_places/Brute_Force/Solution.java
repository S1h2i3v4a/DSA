/*
QUESTION:-
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
*/

/*
APPROACH:-
Use a temporary array of size n. Place each element nums[i] at (i + k) % n in the temp array, then copy temp back to nums.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }
        for (int i = 0; i < n; i++) nums[i] = temp[i];
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
