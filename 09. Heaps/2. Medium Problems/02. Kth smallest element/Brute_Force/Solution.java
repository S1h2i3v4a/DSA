/*
QUESTION:
Given an array `arr[]` and an integer `k`, find the `k`th smallest element in the given array.

APPROACH:
- Sort the array in ascending order.
- The `k`th smallest element is at index `k - 1`.

TIME COMPLEXITY: O(N log N) for sorting.
SPACE COMPLEXITY: O(1) in-place sorting.
*/

import java.util.Arrays;

class Solution {
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        Arrays.sort(arr, l, r + 1);
        return arr[l + k - 1];
    }
}
