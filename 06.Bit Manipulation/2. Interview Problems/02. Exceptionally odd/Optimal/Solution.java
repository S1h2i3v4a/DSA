/*
QUESTION:-
Given an array of N positive integers where all numbers occur an even number of times except one number which occurs an odd number of times. Find that number.

Example:
Input: N = 7, Arr[] = {1, 2, 3, 2, 3, 1, 3}
Output: 3
*/

/*
APPROACH:-
XOR all elements: Since a ^ a = 0 and a ^ 0 = a, XORing all elements cancels out all numbers appearing an even number of times, leaving the single odd-occurring number.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
