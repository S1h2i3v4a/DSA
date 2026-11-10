/*
QUESTION:-
Given a sorted array arr[] of size N without duplicates, and given a value x. Floor of x is defined as the largest element K in arr[] such that K is smaller than or equal to x. Find the index of K (0-based indexing).

Example 1:
Input: N = 7, x = 0, arr[] = {1,2,8,10,11,12,19}
Output: -1
Explanation: No element less than 0 is found. So the output is -1.

Example 2:
Input: N = 7, x = 5, arr[] = {1,2,8,10,11,12,19}
Output: 1
Explanation: Largest number less than 5 is 2 (whose index is 1).
*/

/*
APPROACH:-
Binary Search: Initialize low = 0, high = n - 1, and ans = -1. When arr[mid] <= x, record ans = mid and move right (low = mid + 1) to find an even larger floor value. Otherwise move left (high = mid - 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static int findFloor(long arr[], int n, long x) {
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
