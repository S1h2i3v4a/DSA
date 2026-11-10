/*
QUESTION:-
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. Return the number of unique elements.

Example:
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
*/

/*
APPROACH:-
Insert all elements into a LinkedHashSet to preserve sorted order and eliminate duplicates, then copy back to nums.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int x : nums) set.add(x);
        int idx = 0;
        for (int x : set) nums[idx++] = x;
        return set.size();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
