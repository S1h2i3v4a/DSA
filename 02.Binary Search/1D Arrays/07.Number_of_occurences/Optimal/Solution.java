/*
QUESTION:-
Given a sorted array Arr of size N and a number X, find the number of occurrences of X in Arr.

Example:
Input: N = 7, X = 2, Arr[] = {1, 1, 2, 2, 2, 2, 3}
Output: 4
Explanation: 2 occurs 4 times in the given array.
*/

/*
APPROACH:-
Binary Search: Find the first occurrence and last occurrence of X using binary search. If first occurrence exists, count = last - first + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int count(int[] arr, int n, int x) {
        int first = findBound(arr, n, x, true);
        if (first == -1) return 0;
        int last = findBound(arr, n, x, false);
        return last - first + 1;
    }

    private int findBound(int[] arr, int n, int x, boolean isFirst) {
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) {
                ans = mid;
                if (isFirst) high = mid - 1;
                else low = mid + 1;
            } else if (arr[mid] < x) low = mid + 1;
            else high = mid - 1;
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
