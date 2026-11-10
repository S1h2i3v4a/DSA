/*
QUESTION:-
Given an array containing N integers (both positive, negative, and zeros) and an integer K, find the length of the longest Sub-Array with the sum of the elements equal to the given value K.

Example:
Input: A[] = {-1, 2, 3}, K = 2
Output: 2
*/

/*
APPROACH:-
Prefix Sum with HashMap: Store earliest index of each prefix sum. If current prefix sum == K, maxLen = i + 1. If (prefixSum - K) is in map, update maxLen = max(maxLen, i - map.get(prefixSum - K)).
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
            if (sum == K) {
                maxLen = i + 1;
            } else if (map.containsKey(sum - K)) {
                maxLen = Math.max(maxLen, i - map.get(sum - K));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
