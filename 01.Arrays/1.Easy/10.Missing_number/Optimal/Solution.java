/*
QUESTION:-
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example:
Input: nums = [3,0,1]
Output: 2
*/

/*
APPROACH:-
Formula: Sum of first n natural numbers is n * (n + 1) / 2. The missing number equals expected sum minus actual array sum.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expected = n * (n + 1) / 2;
        int actual = 0;
        for (int x : nums) actual += x;
        return expected - actual;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
