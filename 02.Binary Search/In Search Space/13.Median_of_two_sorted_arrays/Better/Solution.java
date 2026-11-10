/*
QUESTION:-
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

Example:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
*/

/*
APPROACH:-
Two Pointers Simulation: Without storing the merged array, maintain a counter and two pointers to simulate the merge step until reaching index (m + n)/2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int total = n1 + n2;
        int idx2 = total / 2;
        int idx1 = idx2 - 1;
        int el1 = -1, el2 = -1;
        int i = 0, j = 0, cnt = 0;

        while (i < n1 && j < n2) {
            int val;
            if (nums1[i] <= nums2[j]) val = nums1[i++];
            else val = nums2[j++];
            if (cnt == idx1) el1 = val;
            if (cnt == idx2) el2 = val;
            cnt++;
        }
        while (i < n1) {
            int val = nums1[i++];
            if (cnt == idx1) el1 = val;
            if (cnt == idx2) el2 = val;
            cnt++;
        }
        while (j < n2) {
            int val = nums2[j++];
            if (cnt == idx1) el1 = val;
            if (cnt == idx2) el2 = val;
            cnt++;
        }

        if (total % 2 == 1) return (double) el2;
        return ((double) el1 + (double) el2) / 2.0;
    }
}

// TIME COMPLEXITY = O(M + N)
// SPACE COMPLEXITY = O(1)
