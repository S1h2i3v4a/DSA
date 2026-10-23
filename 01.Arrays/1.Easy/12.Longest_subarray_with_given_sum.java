/*
QUESTION:-
You are given an array 'A' of size 'N' and an integer 'K'. You need to print the length of the longest subarray of array 'A' whose sum = 'K'.
Example:
Input: 'N' = 7 'K' = 3
'A' = [1, 2, 3, 1, 1, 1, 1]
Output: 3
Explanation: Subarrays whose sum = '3' are:
[1, 2], [3], [1, 1, 1], [1, 1, 1]
Here, the length of the longest subarray is 3, which is our final answer.
*/

/*
APPROACH:-
-> Use sliding window approach using two pointers start and end
-> Run a loop to traverse the entire array add from end and subtract from start when sum>k
-> If sum==k then, update the ans now, window size = end-start+1
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int longestSubarrayWithSumK(int[] a, long k) {
        int n = a.length;
        int left = 0, right = 0;
        long sum = a[0];
        int maxLen = 0;
        while (right < n) {
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if (right < n) sum += a[right];
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(0)
