/*
QUESTION:-
Given an unsorted array Arr[] of N integers and an integer X, find floor and ceiling of X in Arr[0..N-1].
Floor of X is the largest element which is smaller than or equal to X.
Ceil of X is the smallest element which is greater than or equal to X.

Example:
Input: N = 8, X = 7, Arr[] = {5, 6, 8, 9, 6, 5, 5, 6}
Output: 6 8
Explanation: Floor of 7 is 6 and ceil of 7 is 8.
*/

/*
APPROACH:-
Linear traversal: Maintain floor = -1 and ceil = -1. For each element, update floor if arr[i] <= X and arr[i] > floor. Update ceil if arr[i] >= X and (ceil == -1 || arr[i] < ceil).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int[] getFloorAndCeil(int[] arr, int n, int x) {
        int floor = -1, ceil = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= x) {
                if (floor == -1 || arr[i] > floor) {
                    floor = arr[i];
                }
            }
            if (arr[i] >= x) {
                if (ceil == -1 || arr[i] < ceil) {
                    ceil = arr[i];
                }
            }
        }
        return new int[]{floor, ceil};
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
