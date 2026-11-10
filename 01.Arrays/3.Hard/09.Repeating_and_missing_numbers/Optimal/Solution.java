/*
QUESTION:-
Given an unsorted array of size n of positive integers. One number 'A' from set {1, 2, …n} is missing and one number 'B' occurs twice in array. Find these two numbers.

Example:
Input: [3, 1, 2, 5, 3]
Output: [3, 4] (Repeating: 3, Missing: 4)
*/

/*
APPROACH:-
Math equations: S - Sn = (x - y) and S2 - S2n = (x^2 - y^2) = (x - y)(x + y). Compute (x + y) = (S2 - S2n) / (x - y). Solve linear equations to find x (repeating) and y (missing).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int[] findTwoElement(int arr[], int n) {
        long N = n;
        long Sn = N * (N + 1) / 2;
        long S2n = N * (N + 1) * (2 * N + 1) / 6;
        long S = 0, S2 = 0;
        for (int val : arr) {
            S += val;
            S2 += (long) val * val;
        }
        long diff = S - Sn; // x - y
        long diffSq = S2 - S2n; // x^2 - y^2
        long sum = diffSq / diff; // x + y
        long x = (diff + sum) / 2; // repeating
        long y = sum - x; // missing
        return new int[] { (int) x, (int) y };
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
