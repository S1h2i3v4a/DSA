/*
QUESTION:-
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Return the kth positive integer that is missing from this array.

Example:
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,...]. The 5th missing integer is 9.
*/

/*
APPROACH:-
Linear Scan: For each element in arr, if arr[i] <= k, then k must be shifted by 1 because that number is present and not missing. Otherwise, break and return current k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findKthPositive(int[] arr, int k) {
        for (int num : arr) {
            if (num <= k) k++;
            else break;
        }
        return k;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
