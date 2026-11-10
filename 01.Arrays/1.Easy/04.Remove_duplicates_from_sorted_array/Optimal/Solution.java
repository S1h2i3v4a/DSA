/*
QUESTION:-
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. Return the number of unique elements.

Example:
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
*/

/*
APPROACH:-
Two-pointer technique: Pointer i marks the last placed unique element. Iterate pointer j through the array; whenever nums[j] != nums[i], advance i and set nums[i] = nums[j].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
