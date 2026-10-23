/*
QUESTION:
You are given a number N. Find the total count of set bits for all numbers from 1 to N (both inclusive).

Example:
Input: N = 4
Output: 5
Explanation:
For numbers from 1 to 4:
- For 1: 0 0 1 = 1 set bit
- For 2: 0 1 0 = 1 set bit
- For 3: 0 1 1 = 2 set bits
- For 4: 1 0 0 = 1 set bit
Therefore, the total set bits is 5.

APPROACH:
The approach to solve this problem is based on the observation that the count of set bits in the binary representation of a number `n` can be determined by the following formula:
countSetBits(n) = countSetBits(pow(2, x - 1) * x) + (n - pow(2, x) + 1) + countSetBits(n - pow(2, x))

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int countSetBits(int n) {
        if (n == 0) return 0;
        int x = largestPowerOf2(n);
        int b2x = x * (1 << (x - 1));
        int msb2ton = n - (1 << x) + 1;
        int rest = n - (1 << x);
        return b2x + msb2ton + countSetBits(rest);
    }
    private static int largestPowerOf2(int n) {
        int x = 0;
        while ((1 << x) <= n) x++;
        return x - 1;
    }
}

// TIME COMPLEXITY = O(log(n))
// SPACE COMPLEXITY = O(log(n))
