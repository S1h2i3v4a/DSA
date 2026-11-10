/*
QUESTION:-
Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K.
Find the element that would be at the kth position of the final sorted array.

Example:
Input: arr1[] = {2, 3, 6, 7, 9}, arr2[] = {1, 4, 8, 10}, k = 5
Output: 6
Explanation: The final sorted array would be [1, 2, 3, 4, 6, 7, 8, 9, 10]. The 5th element is 6.
*/

/*
APPROACH:-
Binary Search on Partitions: Partition arr1 and arr2 such that cut1 + cut2 == k. Use binary search over range [max(0, k - n2), min(k, n1)] in O(log(min(N, M))).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public long kthElement(int k, int arr1[], int arr2[]) {
        int n1 = arr1.length, n2 = arr2.length;
        if (n1 > n2) return kthElement(k, arr2, arr1);

        int low = Math.max(0, k - n2), high = Math.min(k, n1);

        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = k - mid1;

            int l1 = (mid1 > 0) ? arr1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? arr2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? arr1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? arr2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(log(min(N, M)))
// SPACE COMPLEXITY = O(1)
