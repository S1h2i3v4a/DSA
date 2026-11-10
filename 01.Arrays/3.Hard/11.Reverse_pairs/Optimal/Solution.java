/*
QUESTION:-
Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].

Example:
Input: nums = [1,3,2,3,1]
Output: 2
*/

/*
APPROACH:-
Modified Merge Sort: Before merging the two sorted halves [low..mid] and [mid+1..high], count pairs using two pointers where nums[i] > 2L * nums[j]. Then merge normally.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }
    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high) return 0;
        int mid = (low + high) / 2;
        int count = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high);
        count += countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);
        return count;
    }
    private int countPairs(int[] nums, int low, int mid, int high) {
        int count = 0, right = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) nums[i] > 2L * nums[right]) {
                right++;
            }
            count += (right - (mid + 1));
        }
        return count;
    }
    private void merge(int[] nums, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;
        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) temp.add(nums[left++]);
            else temp.add(nums[right++]);
        }
        while (left <= mid) temp.add(nums[left++]);
        while (right <= high) temp.add(nums[right++]);
        for (int i = low; i <= high; i++) nums[i] = temp.get(i - low);
    }
}

// TIME COMPLEXITY = O(2N log N)
// SPACE COMPLEXITY = O(N)
