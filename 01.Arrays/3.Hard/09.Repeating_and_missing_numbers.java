/*
QUESTION:
Given an unsorted array Arr of size N of positive integers. One number 'A' from set {1, 2,....,N} is missing and one number 'B' occurs twice in the array. Find these two numbers.

Example:
Input:
N = 2
Arr[] = {2, 2}
Output: 2 1
Explanation: Repeating number is 2 and the smallest positive missing number is 1.

APPROACH:
To find the missing and repeating numbers in the given unsorted array, we can utilize the properties of summation and sum of squares. Let's denote the missing number as 'x' and the repeating number as 'y'.

1. Calculate the optimal sum 'optSum' using the formula: optSum = N * (N + 1) / 2, where N is the size of the array.
2. Calculate the optimal sum of squares 'opt2Sum' using the formula: opt2Sum = N * (N + 1) * (2 * N + 1) / 6.
3. Calculate the actual sum 'actSum' and actual sum of squares 'act2Sum' of the given array.
4. Find the difference between the optimal sum and the actual sum: xMinusY = optSum - actSum.
5. Find the difference between the optimal sum of squares and the actual sum of squares: x2MinusY2 = opt2Sum - act2Sum.
6. Calculate the sum of 'x' and 'y': xPlusY = x2MinusY2 / xMinusY.
7. Calculate 'x' and 'y' using the equations: x = (xPlusY + xMinusY) / 2 and y = xPlusY - x.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int[] findTwoElement(int arr[], int n) {
        long s = 0, s2 = 0;
        long sn = (long) n * (n + 1) / 2;
        long s2n = (long) n * (n + 1) * (2 * n + 1) / 6;
        for (int i = 0; i < n; i++) {
            s += arr[i];
            s2 += (long) arr[i] * (long) arr[i];
        }
        long val1 = s - sn;
        long val2 = s2 - s2n;
        val2 = val2 / val1;
        long x = (val1 + val2) / 2;
        long y = x - val1;
        return new int[]{(int) x, (int) y};
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
