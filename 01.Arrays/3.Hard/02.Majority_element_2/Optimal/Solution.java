/*
QUESTION:-
Given an integer array of size n, find all elements that appear more than floor(n/3) times.

Example:
Input: nums = [3,2,3]
Output: [3]
*/

/*
APPROACH:-
Extended Boyer-Moore Voting Algorithm: There can be at most two elements appearing > n/3 times. Maintain two candidate variables (cand1, cand2) and two counters (c1, c2). Then run a verification pass.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int cand1 = 0, cand2 = 0, c1 = 0, c2 = 0;
        for (int x : nums) {
            if (x == cand1) {
                c1++;
            } else if (x == cand2) {
                c2++;
            } else if (c1 == 0) {
                cand1 = x;
                c1 = 1;
            } else if (c2 == 0) {
                cand2 = x;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = 0; c2 = 0;
        for (int x : nums) {
            if (x == cand1) c1++;
            else if (x == cand2) c2++;
        }
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        if (c1 > n / 3) ans.add(cand1);
        if (c2 > n / 3) ans.add(cand2);
        return ans;
    }
}

// TIME COMPLEXITY = O(2N)
// SPACE COMPLEXITY = O(1)
