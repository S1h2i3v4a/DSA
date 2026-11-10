/*
QUESTION:-
Given an array containing N integers and an integer K, find the length of the longest Sub-Array with the sum of the elements equal to the given value K.

Example:
Input: A[] = {10, 5, 2, 7, 1, 9}, K = 15
Output: 4
*/

/*
APPROACH:-
Prefix Sum with HashMap: Store earliest occurrence index of each prefix sum. If (sum - K) exists in map, update maxLen = max(maxLen, i - map.get(sum - K)).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int lenOfLongSubarr(int A[], int N, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            if (sum == K) maxLen = i + 1;
            if (map.containsKey(sum - K)) {
                maxLen = Math.max(maxLen, i - map.get(sum - K));
            }
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
