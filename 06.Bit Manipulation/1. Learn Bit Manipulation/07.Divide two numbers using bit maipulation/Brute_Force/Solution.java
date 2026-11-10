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
Repeated Subtraction: Subtract divisor from dividend repeatedly while dividend >= divisor, incrementing quotient by 1 each time.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean negative = (dividend < 0) ^ (divisor < 0);
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        long quotient = 0;
        while (dvd >= dvs) {
            dvd -= dvs;
            quotient++;
        }
        return negative ? (int) -quotient : (int) quotient;
    }
}

// TIME COMPLEXITY = O(dividend / divisor)
// SPACE COMPLEXITY = O(1)
