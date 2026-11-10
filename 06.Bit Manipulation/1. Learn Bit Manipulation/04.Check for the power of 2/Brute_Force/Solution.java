/*
QUESTION:-
Given a non-negative integer N. The task is to check if N is a power of 2.

Example:
Input: N = 8
Output: true
*/

/*
APPROACH:-
Repeated Division: While n > 1 and n % 2 == 0, divide n by 2. If n becomes 1, it is a power of 2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean isPowerOfTwo(long n) {
        if (n <= 0) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
