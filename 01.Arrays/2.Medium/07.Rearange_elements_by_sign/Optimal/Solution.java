/*
QUESTION:-
Given an integer array nums of even length consisting of an equal number of positive and negative integers. Rearrange the elements so that they alternate in sign, starting with a positive number, preserving relative order.

Example:
Input: nums = [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
*/

/*
APPROACH:-
Single Pass with pointers: Maintain posIndex = 0 and negIndex = 1 in the result array. Iterate nums once; if positive, place at posIndex and posIndex += 2; if negative, place at negIndex and negIndex += 2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];
        int posIndex = 0, negIndex = 1;
        for (int x : nums) {
            if (x > 0) {
                ans[posIndex] = x;
                posIndex += 2;
            } else {
                ans[negIndex] = x;
                negIndex += 2;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
