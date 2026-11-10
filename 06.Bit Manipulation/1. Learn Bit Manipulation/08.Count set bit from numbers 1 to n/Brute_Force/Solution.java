/*
QUESTION:-
You are given a number N. Find the total count of set bits for all numbers from 1 to N(both inclusive).

Example:
Input: N = 4
Output: 5
Explanation: 1 (01), 2 (10), 3 (11), 4 (100). Total set bits = 1 + 1 + 2 + 1 = 5.
*/

/*
APPROACH:-
Iterate 1 to N: For each integer i, count set bits using Brian Kernighan's algorithm or bit counting, summing up to total.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int countSetBits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp > 0) {
                temp = temp & (temp - 1);
                count++;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(1)
