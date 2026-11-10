/*
QUESTION:-
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Example:
Input: nums = [4,1,2,1,2]
Output: 4
*/

/*
APPROACH:-
XOR property: x ^ x = 0 and x ^ 0 = x. XORing all elements cancels out pairs and isolates the single number.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int x : nums) xor ^= x;
        return xor;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
