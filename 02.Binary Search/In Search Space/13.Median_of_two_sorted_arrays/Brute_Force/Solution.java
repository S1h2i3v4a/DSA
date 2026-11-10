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
Merge into new array: Merge nums1 and nums2 into a single sorted array of size m + n. Then return the median directly from the merged array.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] merged = new int[n1 + n2];
        int i = 0, j = 0, k = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) merged[k++] = nums1[i++];
            else merged[k++] = nums2[j++];
        }
        while (i < n1) merged[k++] = nums1[i++];
        while (j < n2) merged[k++] = nums2[j++];

        int total = n1 + n2;
        if (total % 2 == 1) return (double) merged[total / 2];
        return ((double) merged[total / 2] + (double) merged[total / 2 - 1]) / 2.0;
    }
}

// TIME COMPLEXITY = O(M + N)
// SPACE COMPLEXITY = O(M + N)
