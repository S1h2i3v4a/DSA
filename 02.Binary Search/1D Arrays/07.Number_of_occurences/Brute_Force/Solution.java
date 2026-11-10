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
Linear count: Traverse the array and count how many times X appears.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int count(int[] arr, int n, int x) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) cnt++;
        }
        return cnt;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
