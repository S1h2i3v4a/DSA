/*
Question:
You are given two integers L and R, your task is to find the XOR of elements of the range [L, R].

Example:

Input:
L = 4, R = 8
Output:
8
Explanation:
4 ^ 5 ^ 6 ^ 7 ^ 8 = 8
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int findXOR(int l, int r) {
        return computeXOR(l - 1) ^ computeXOR(r);
    }
    private static int computeXOR(int n) {
        if (n % 4 == 0) return n;
        if (n % 4 == 1) return 1;
        if (n % 4 == 2) return n + 1;
        return 0;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
