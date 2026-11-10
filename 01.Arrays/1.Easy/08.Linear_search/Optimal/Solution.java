/*
QUESTION:-
Given an array arr[] sorted in ascending order of size N and an integer K. Check if K is present in the array or not. Return 1 if found, else -1.

Example:
Input: N = 5, K = 6, arr[] = {1,2,3,4,6}
Output: 1
*/

/*
APPROACH:-
Iterate sequentially through the array from 0 to N-1. If arr[i] == K, return 1. If loop terminates without match, return -1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int searchInSorted(int arr[], int N, int K) {
        for (int i = 0; i < N; i++) {
            if (arr[i] == K) return 1;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
