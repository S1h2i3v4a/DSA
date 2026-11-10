/*
QUESTION:-
You are given two integers L and R, your task is to find the XOR of elements of the range [L, R].

Example:
Input: L = 4, R = 8
Output: 8
*/

/*
APPROACH:-
Linear Traversal: Loop from L to R and compute cumulative XOR.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int findXOR(int l, int r) {
        int ans = 0;
        for (int i = l; i <= r; i++) {
            ans ^= i;
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(R - L)
// SPACE COMPLEXITY = O(1)
