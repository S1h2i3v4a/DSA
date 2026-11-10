/*
QUESTION:-
Given a non-negative integer N. The task is to check if N is a power of 2.

Example:
Input: N = 8
Output: true
*/

/*
APPROACH:-
Bit Trick n & (n - 1): A power of 2 has exactly one set bit in binary. Subtracting 1 flips all bits after that set bit (and the set bit itself). Therefore, n & (n - 1) == 0 for all powers of 2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean isPowerOfTwo(long n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
