/*
QUESTION:-
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order 0, 1, and 2.

Example:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/

/*
APPROACH:-
Sort the array using standard library sort (dual-pivot quicksort / mergesort).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(1)
