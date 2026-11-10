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
Bitwise Trick N | (N + 1): Adding 1 to N flips all consecutive trailing 1s to 0 and the first unset bit to 1. Performing bitwise OR between N and N + 1 sets this bit in O(1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static int setBit(int N) {
        return N | (N + 1);
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
