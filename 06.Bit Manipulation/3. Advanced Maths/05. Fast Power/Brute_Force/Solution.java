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
Iterative Multiplication: Multiply x, |n| times in a loop. Invert if n < 0.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1.0 / x;
            exp = -exp;
        }
        double ans = 1.0;
        for (long i = 0; i < exp; i++) {
            ans *= x;
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(|n|)
// SPACE COMPLEXITY = O(1)
