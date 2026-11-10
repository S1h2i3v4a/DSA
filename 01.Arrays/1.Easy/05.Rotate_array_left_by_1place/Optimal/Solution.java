/*
QUESTION:-
Given an array nums of size n, rotate the array to the left by one position in-place.

Example:
Input: nums = [1,2,3,4,5], n = 5
Output: [2,3,4,5,1]
*/

/*
APPROACH:-
Store nums[0] in a temp variable. Shift all elements left by one position (nums[i-1] = nums[i]), then assign temp to nums[n-1].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void rotate(int[] nums, int n) {
        if (n <= 1) return;
        int temp = nums[0];
        for (int i = 1; i < n; i++) {
            nums[i - 1] = nums[i];
        }
        nums[n - 1] = temp;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
