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
Check all 32 bits: For every bit position from 0 to 31, compare the bit of start with the bit of goal. If they differ, increment flips.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int minBitFlips(int start, int goal) {
        int flips = 0;
        for (int i = 0; i < 32; i++) {
            if (((start >> i) & 1) != ((goal >> i) & 1)) {
                flips++;
            }
        }
        return flips;
    }
}

// TIME COMPLEXITY = O(32) = O(1)
// SPACE COMPLEXITY = O(1)
