/*
QUESTION:-
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements. Must do this in-place.

Example:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
*/

/*
APPROACH:-
Extract all non-zero elements into an ArrayList. Write them back into nums, and fill remaining indices with zeros.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void moveZeroes(int[] nums) {
        List<Integer> nonZeroes = new ArrayList<>();
        for (int num : nums) {
            if (num != 0) nonZeroes.add(num);
        }
        for (int i = 0; i < nonZeroes.size(); i++) {
            nums[i] = nonZeroes.get(i);
        }
        for (int i = nonZeroes.size(); i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
