/*QUESTION:
Given two sorted arrays nums1 and nums2 of sizes m and n respectively, you need to find the median of the two sorted arrays.

APPROACH:
To find the median of two sorted arrays, we can apply the concept of binary search. The overall time complexity of the solution should be O(log (m+n)).

1. First, we ensure that nums1 is the smaller sized array. If not, we swap nums1 and nums2.
2. Calculate the optimal count opt_cnt, which is (nums1.size() + nums2.size() + 1) / 2. This represents the median index in the merged array.
3. Initialize low to 0 and high to nums1.size() - 1.
4. Perform binary search until low is less than or equal to high:
     - Calculate the cut1 as low + (high - low) / 2, which represents the potential index to partition nums1.
     - Calculate cut2 as opt_cnt - cut1, which represents the corresponding index in nums2.
     - Calculate l1, l2, r1, and r2 as the left and right elements around the potential partition points.
     - If l1 is less than or equal to r2 and l2 is less than or equal to r1, it means we have found the correct partition points.
          - If the total number of elements is even, return the average of the maximum of l1 and l2 and the minimum of r1 and r2.
          - If the total number of elements is odd, return the maximum of l1 and l2.
     - If l1 is greater than r2, move the high pointer to cut1 - 1.
     - Otherwise, move the low pointer to cut1 + 1.
5. If no median is found, return 0.0.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int n1 = nums1.length, n2 = nums2.length;
        int low = 0, high = n1;
        int left = (n1 + n2 + 1) / 2;

        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = left - mid1;

            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                if ((n1 + n2) % 2 == 1) return Math.max(l1, l2);
                return ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0.0;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
