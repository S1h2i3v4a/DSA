/*
Question:-
Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K.
Find the element that would be at the kth position of the final sorted array.

Example:- 

Input:
arr1[] = {2, 3, 6, 7, 9}
arr2[] = {1, 4, 8, 10}
k = 5
Output:
6
Explanation:
The final sorted array would be -
1, 2, 3, 4, 6, 7, 8, 9, 10
The 5th element of this array is 6.

Approach:
1. Compare the sizes of the two arrays, arr1 and arr2. If the size of arr1 is greater than arr2, swap the arrays to ensure arr1 is the smaller sized array.
2. Set the low and high variables for binary search.
   - If m < k, set low = k - m, otherwise set low = 0.
   - If k < n, set high = k, otherwise set high = n.
3. Perform binary search within the range [low, high] to find the kth element.
4. In each iteration of binary search:
   - Calculate the cut positions, cut1 and cut2, based on the mid position.
   - Determine the left and right elements of arr1 and arr2 based on the cut positions.
   - Compare the left and right elements and adjust the low and high pointers accordingly.
5. Return the maximum element among the left elements, as it would be the kth element in the final sorted array.

CODE
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
