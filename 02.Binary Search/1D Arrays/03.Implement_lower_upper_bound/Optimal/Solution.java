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
Sort the array first in O(N log N). Then use binary search twice: once for floor (largest element <= x) and once for ceil (smallest element >= x).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int[] getFloorAndCeil(int[] arr, int n, int x) {
        Arrays.sort(arr);
        int floor = -1, ceil = -1;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= x) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        low = 0; high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{floor, ceil};
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(1)
