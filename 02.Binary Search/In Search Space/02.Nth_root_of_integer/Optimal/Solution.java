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
Binary Search: Search in range [1, m]. Calculate mid. Multiply mid n times (stopping early if product exceeds m). If product == m, return mid. If product < m, low = mid + 1; else high = mid - 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int NthRoot(int n, int m) {
        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long val = power(mid, n, m);
            if (val == 1) return mid;
            else if (val == 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    private long power(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) return 2; // exceeds m
        }
        if (ans == m) return 1;
        return 0; // less than m
    }
}

// TIME COMPLEXITY = O(n * log m)
// SPACE COMPLEXITY = O(1)
