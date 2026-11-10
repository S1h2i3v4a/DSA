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
Fast Modular Exponentiation: There are (n + 1)/2 even positions (5 choices each) and n/2 odd positions (4 choices each). Compute (5^((n+1)/2) * 4^(n/2)) % (10^9+7) using binary exponentiation.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    private static final long MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;
        long first = power(5, even);
        long second = power(4, odd);
        return (int) ((first * second) % MOD);
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
