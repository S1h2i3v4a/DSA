/*
QUESTION:-
Given an ascending sorted rotated array Arr of distinct integers of size N. The array is right rotated K times. Find the value of K.

Example:
Input: N = 5, Arr[] = {5, 1, 2, 3, 4}
Output: 1
Explanation: The original sorted array is 1 2 3 4 5, rotated 1 time to the right.
*/

/*
APPROACH:-
Binary Search: Find the index of the minimum element using binary search. If a subarray is sorted, arr[low] could be the minimum; otherwise check which half is unsorted and proceed.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int findKRotation(int arr[], int n) {
        int low = 0, high = n - 1;
        int minIdx = 0;
        while (low <= high) {
            if (arr[low] <= arr[high]) {
                if (arr[low] < arr[minIdx]) minIdx = low;
                break;
            }
            int mid = low + (high - low) / 2;
            if (arr[mid] < arr[minIdx]) minIdx = mid;
            if (arr[low] <= arr[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return minIdx;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
