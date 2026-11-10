/*
QUESTION:-
You are given 2 numbers (n, m); the task is to find n-th root of m. If the n-th root is not an integer, return -1.

Example 1:
Input: n = 2, m = 9
Output: 3
Explanation: 3^2 = 9

Example 2:
Input: n = 3, m = 9
Output: -1
Explanation: 3rd root of 9 is not an integer.
*/

/*
APPROACH:-
Linear Search: Iterate i from 1 to m. Compute i^n. If i^n == m, return i. If i^n > m, break and return -1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int NthRoot(int n, int m) {
        for (int i = 1; i <= m; i++) {
            long val = 1;
            for (int j = 1; j <= n; j++) {
                val *= i;
                if (val > m) break;
            }
            if (val == m) return i;
            if (val > m) break;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(m^(1/n) * n)
// SPACE COMPLEXITY = O(1)
