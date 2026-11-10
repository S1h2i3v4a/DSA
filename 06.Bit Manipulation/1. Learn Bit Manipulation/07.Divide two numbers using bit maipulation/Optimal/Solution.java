/*
QUESTION:-
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
Return the quotient after dividing dividend by divisor. Clamp overflow to [-2^31, 2^31 - 1].

Example:
Input: dividend = 10, divisor = 3
Output: 3
*/

/*
APPROACH:-
Bit Shifting (Exponential Doubling): Shift the divisor left by powers of 2 (divisor << i) until it exceeds dividend. Subtract and accumulate quotient in O(log^2 N) or O(32).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        long quotient = 0;

        for (int i = 31; i >= 0; i--) {
            if ((dvd >= (dvs << i))) {
                dvd -= (dvs << i);
                quotient += (1L << i);
            }
        }
        return isNegative ? (int) -quotient : (int) quotient;
    }
}

// TIME COMPLEXITY = O(32) = O(1)
// SPACE COMPLEXITY = O(1)
