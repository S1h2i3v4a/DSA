/*
QUESTION:-
Given an integer array nums of even length consisting of an equal number of positive and negative integers. Rearrange the elements so that they alternate in sign, starting with a positive number, preserving relative order.

Example:
Input: nums = [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
*/

/*
APPROACH:-
Extract positives and negatives into two separate lists. Fill the result array by placing pos[i] at 2*i and neg[i] at 2*i + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] rearrangeArray(int[] nums) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for (int x : nums) {
            if (x > 0) pos.add(x);
            else neg.add(x);
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < pos.size(); i++) {
            ans[2 * i] = pos.get(i);
            ans[2 * i + 1] = neg.get(i);
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
