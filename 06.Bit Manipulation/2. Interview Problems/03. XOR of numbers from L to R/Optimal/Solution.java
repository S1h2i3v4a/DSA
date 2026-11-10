/*
QUESTION:-
You are given two integers L and R, your task is to find the XOR of elements of the range [L, R].

Example:
Input: L = 4, R = 8
Output: 8
*/

/*
APPROACH:-
O(1) Pattern Matching: XOR from 1 to N repeats in cycles of 4:
If n % 4 == 0: n
If n % 4 == 1: 1
If n % 4 == 2: n + 1
If n % 4 == 3: 0
XOR(L..R) = XOR(1..L-1) ^ XOR(1..R).
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

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
