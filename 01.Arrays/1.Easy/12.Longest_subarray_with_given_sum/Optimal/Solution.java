/*
QUESTION:-
Given an array containing N integers and an integer K, find the length of the longest Sub-Array with the sum of the elements equal to the given value K.

Example:
Input: A[] = {10, 5, 2, 7, 1, 9}, K = 15
Output: 4
*/

/*
APPROACH:-
Two-pointer / Sliding Window (for non-negative numbers): Expand right pointer adding A[right]. While sum > K, shrink left pointer. When sum == K, update maxLen.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int lenOfLongSubarr(int A[], int N, int K) {
        int left = 0, right = 0, sum = 0, maxLen = 0;
        while (right < N) {
            sum += A[right];
            while (left <= right && sum > K) {
                sum -= A[left++];
            }
            if (sum == K) maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(2N)
// SPACE COMPLEXITY = O(1)
