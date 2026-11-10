/*
QUESTION:-
Given an array of integers. Find the Inversion Count in the array. Two array elements arr[i] and arr[j] form an inversion if arr[i] > arr[j] and i < j.

Example:
Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3
*/

/*
APPROACH:-
Modified Merge Sort: During the merge step of sorted halves, if arr[left] > arr[right], then all remaining elements in left half (mid - left + 1) are also greater than arr[right]. Add to inversion count.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static long inversionCount(long arr[], int n) {
        return mergeSort(arr, 0, n - 1);
    }
    private static long mergeSort(long[] arr, int low, int high) {
        long count = 0;
        if (low >= high) return count;
        int mid = (low + high) / 2;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += merge(arr, low, mid, high);
        return count;
    }
    private static long merge(long[] arr, int low, int mid, int high) {
        List<Long> temp = new ArrayList<>();
        int left = low, right = mid + 1;
        long count = 0;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
                count += (mid - left + 1);
            }
        }
        while (left <= mid) temp.add(arr[left++]);
        while (right <= high) temp.add(arr[right++]);
        for (int i = low; i <= high; i++) arr[i] = temp.get(i - low);
        return count;
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(N)
