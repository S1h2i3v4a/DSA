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
Binary Search: For any index mid, the number of missing numbers up to arr[mid] is arr[mid] - (mid + 1). Use binary search to find the index where missing < k. Finally, result = low + k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
