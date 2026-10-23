/*
QUESTION:
Given an integer array nums, return the number of reverse pairs in the array.
A reverse pair is a pair (i, j) where:
0 <= i < j < nums.length and
nums[i] > 2 * nums[j].

Example:
Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1

APPROACH:
To solve this problem, we can use the merge sort algorithm. While merging the two sorted subarrays, we can count the number of reverse pairs.

1. Define a variable 'rev_pair' to store the count of reverse pairs.
2. Implement the 'merge' function to merge two subarrays and count the reverse pairs.
3. Implement the 'mergesort' function to recursively divide the array into subarrays and perform merge sort.
4. Initialize 'rev_pair' to 0 and call the 'mergesort' function on the given array.
5. Return the 'rev_pair' as the result.

CODE:
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
        int cnt = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high);
        cnt += countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);
        return cnt;
    }
    private int countPairs(int[] nums, int low, int mid, int high) {
        int right = mid + 1, cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) nums[i] > 2L * nums[right]) right++;
            cnt += (right - (mid + 1));
        }
        return cnt;
    }
    private void merge(int[] nums, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
