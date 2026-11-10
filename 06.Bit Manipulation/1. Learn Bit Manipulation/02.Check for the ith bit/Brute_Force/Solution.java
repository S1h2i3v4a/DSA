/*
QUESTION:-
Given an integer n and an integer k. The task is to check if the kth bit (0-indexed) of n is set or not.

Example:
Input: n = 4, k = 0
Output: No
Explanation: Binary representation of 4 is 100, in which 0th bit from LSB is not set.
*/

/*
APPROACH:-
Right Shift: Right-shift n by k bits and check the least significant bit with bitwise AND 1: (n >> k) & 1 == 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean checkKthBitRightShift(int n, int k) {
        return ((n >> k) & 1) == 1;
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
