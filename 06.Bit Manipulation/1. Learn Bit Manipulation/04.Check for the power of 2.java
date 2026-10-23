/*
QUESTION:-
Given an integer n, return true if it is a power of two. Otherwise, return false.

Example 1:
Input: n = 1
Output: true
Explanation: 2^0 = 1

Example 2:
Input: n = 16
Output: true
Explanation: 2^4 = 16
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean isPowerOfTwo(long n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
