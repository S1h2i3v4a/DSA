/*
QUESTION:
Given an array of N positive integers where all numbers occur an even number of times except one number which occurs an odd number of times. Find the exceptional number.

Example:
Input: N = 7, Arr[] = {1, 2, 3, 2, 3, 1, 3}
Output: 3
Explanation: Number 3 occurs three times, which is an odd number of times, while all other numbers occur an even number of times.

APPROACH:
To find the exceptional number, we can use the bitwise XOR operation. XORing a number with itself results in 0, so XORing all the numbers in the array will cancel out the even occurrences, leaving only the exceptional number in the result.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) result ^= num;
        return result;
    }
}

// TIME COMPLEXITY = O(n), where n is the number of elements in the array
// SPACE COMPLEXITY = O(1)
