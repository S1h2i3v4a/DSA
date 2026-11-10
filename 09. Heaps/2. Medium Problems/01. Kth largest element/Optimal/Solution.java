/*
QUESTION:
Given an integer array `nums` and an integer `k`, return the `k`th largest element in O(N) average time.

APPROACH:
- Use QuickSelect (Hoare's selection algorithm).
- We seek the element at target index `targetIdx = nums.length - k` in a sorted array.
- Partition the array around a pivot:
  - If pivot index == targetIdx, return `nums[pivot]`.
  - If pivot index < targetIdx, search right subarray.
  - Else search left subarray.

TIME COMPLEXITY: O(N) average case, O(N^2) worst case.
SPACE COMPLEXITY: O(1) iterative partition.
*/

class Solution {
    public static int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int pivotIdx = partition(nums, left, right);
            if (pivotIdx == target) {
                return nums[pivotIdx];
            } else if (pivotIdx < target) {
                left = pivotIdx + 1;
            } else {
                right = pivotIdx - 1;
            }
        }
        return -1;
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
