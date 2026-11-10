/*
QUESTION:-
Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:
Input: x = 2.00000, n = -2
Output: 0.25000
*/

/*
APPROACH:-
Binary Exponentiation: Use long N = |n| to prevent overflow with Integer.MIN_VALUE. When power is odd, multiply ans by x; square x and halve the power in each step in O(log N).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1.0 / x;
            N = -N;
        }
        double ans = 1.0;
        while (N > 0) {
            if ((N & 1) == 1) {
                ans *= x;
            }
            x *= x;
            N >>= 1;
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(log |n|)
// SPACE COMPLEXITY = O(1)
