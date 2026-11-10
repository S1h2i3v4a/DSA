/*
QUESTION:-
Given an array of integers A and an integer B. Find the total number of subarrays having bitwise XOR of all elements equals to B.

Example:
Input: A = [4, 2, 2, 6, 4], B = 6
Output: 4
*/

/*
APPROACH:-
Compute XOR for all subarrays using two nested loops. Increment count whenever running XOR == B.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int solve(int[] A, int B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int xor = 0;
            for (int j = i; j < A.length; j++) {
                xor ^= A[j];
                if (xor == B) count++;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
