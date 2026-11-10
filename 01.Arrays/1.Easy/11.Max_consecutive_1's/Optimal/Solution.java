/*
QUESTION:-
Given a binary array nums, return the maximum number of consecutive 1's in the array.

Example:
Input: nums = [1,1,0,1,1,1]
Output: 3
*/

/*
APPROACH:-
Single pass: Maintain count and max. If element is 1, increment count and update max. If element is 0, reset count to 0.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0;
        for (int x : nums) {
            if (x == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
