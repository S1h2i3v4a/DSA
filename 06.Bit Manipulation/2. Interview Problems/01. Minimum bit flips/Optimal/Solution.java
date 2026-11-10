/*
QUESTION:-
A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.
Given two integers start and goal, return the minimum number of bit flips to convert start to goal.

Example:
Input: start = 10, goal = 7
Output: 3
Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We need 3 flips.
*/

/*
APPROACH:-
XOR and Brian Kernighan's Algorithm: Compute diff = start ^ goal. Each set bit in diff represents a bit that must be flipped. Count set bits in diff using diff = diff & (diff - 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int count = 0;
        while (xor != 0) {
            xor = xor & (xor - 1);
            count++;
        }
        return count;
    }
}

// TIME COMPLEXITY = O(number of set bits)
// SPACE COMPLEXITY = O(1)
