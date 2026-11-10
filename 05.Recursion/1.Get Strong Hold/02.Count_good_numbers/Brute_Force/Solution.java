/*
QUESTION:-
A digit string is good if the digits (0-indexed) at even indices are even (0, 2, 4, 6, 8) and the digits at odd indices are prime (2, 3, 5, 7).
Given an integer n, return the total number of good digit strings of length n modulo 10^9 + 7.

Example:
Input: n = 4
Output: 400
Explanation: There are 5 choices for each even position and 4 choices for each odd position: 5 * 4 * 5 * 4 = 400.
*/

/*
APPROACH:-
Linear multiplication: Multiply 5 for even positions and 4 for odd positions one by one in a loop modulo 10^9 + 7.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int countGoodNumbers(long n) {
        long mod = 1_000_000_007;
        long ans = 1;
        for (long i = 0; i < n; i++) {
            if (i % 2 == 0) ans = (ans * 5) % mod;
            else ans = (ans * 4) % mod;
        }
        return (int) ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
