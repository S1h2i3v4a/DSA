/*
QUESTION:-
Given an array nums of size n, return the majority element. The majority element is the element that appears more than floor(n / 2) times.

Example:
Input: nums = [3,2,3]
Output: 3
*/

/*
APPROACH:-
Iterate through each element, count its occurrences across the entire array using an inner loop. Return the element if count > n / 2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) count++;
            }
            if (count > n / 2) return nums[i];
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
