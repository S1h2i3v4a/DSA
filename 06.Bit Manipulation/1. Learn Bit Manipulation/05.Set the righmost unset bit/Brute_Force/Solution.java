/*
QUESTION:-
Given a non-negative number N. The problem is to set the rightmost unset bit in the binary representation of N.

Example:
Input: N = 6
Output: 7
Explanation: Binary of 6 is 110, setting rightmost unset bit gives 111 (7).
*/

/*
APPROACH:-
Linear Bit Scan: Iterate through bit positions from 0 to 31. If the bit at position i is 0, set it using (N | (1 << i)) and return.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static int setBit(int N) {
        for (int i = 0; i < 31; i++) {
            if ((N & (1 << i)) == 0) {
                return N | (1 << i);
            }
        }
        return N;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
