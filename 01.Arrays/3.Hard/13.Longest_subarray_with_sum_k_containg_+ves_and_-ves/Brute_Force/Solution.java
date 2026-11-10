/*
QUESTION:-
Given an array containing N integers (both positive, negative, and zeros) and an integer K, find the length of the longest Sub-Array with the sum of the elements equal to the given value K.

Example:
Input: A[] = {-1, 2, 3}, K = 2
Output: 2
*/

/*
APPROACH:-
Generate all subarrays using two nested loops, calculate sum, and update maxLen when sum == K.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int lenOfLongSubarr(int A[], int N, int K) {
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                if (sum == K) maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
