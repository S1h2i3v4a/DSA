/*
QUESTION:-
You are given a number N. Find the total count of set bits for all numbers from 1 to N(both inclusive).

Example:
Input: N = 4
Output: 5
Explanation: 1 (01), 2 (10), 3 (11), 4 (100). Total set bits = 1 + 1 + 2 + 1 = 5.
*/

/*
APPROACH:-
Largest Power of 2 Recursion: For largest power of 2 (2^x <= n): bits from 0 to 2^x - 1 = x * 2^(x - 1). Most significant bit from 2^x to n appears (n - 2^x + 1) times. Recursively add countSetBits(n - 2^x).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int countSetBits(int n) {
        if (n == 0) return 0;
        int x = largestPowerOf2(n);
        int bitsUpTo2x = x * (1 << (x - 1));
        int msb2xToN = n - (1 << x) + 1;
        int rest = n - (1 << x);
        return bitsUpTo2x + msb2xToN + countSetBits(rest);
    }

    private static int largestPowerOf2(int n) {
        int x = 0;
        while ((1 << x) <= n) x++;
        return x - 1;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(log N)
