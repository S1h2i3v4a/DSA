/*
QUESTION:-
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Return the single element that appears only once.

Example:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
*/

/*
APPROACH:-
Linear XOR traversal: XOR of all elements cancels out pairs (a ^ a = 0), leaving only the single element.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
