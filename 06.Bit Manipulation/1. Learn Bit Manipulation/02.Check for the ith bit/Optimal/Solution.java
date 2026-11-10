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
Left Shift Mask: Create a mask by left-shifting 1 by k bits (1 << k). If (n & (1 << k)) != 0, the k-th bit is set.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean checkKthBitLeftShift(int n, int k) {
        return (n & (1 << k)) != 0;
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
