/*
QUESTION:
Given an array of integers. Find the Inversion Count in the array.

Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If the array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.

Example 1:
Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3
Explanation: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

Example 2:
Input: N = 5, arr[] = {2, 3, 4, 5, 6}
Output: 0
Explanation: As the sequence is already sorted, there is no inversion count.

APPROACH:
To find the inversion count in the array, we can utilize the merge sort algorithm. The idea is to divide the array into two halves, recursively count the inversions in each half, and then merge the two halves while counting the inversions across them.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static long inversionCount(long arr[], long N) {
        return mergeSort(arr, 0, (int) N - 1);
    }
    static long mergeSort(long arr[], int low, int high) {
        long cnt = 0;
        if (low >= high) return cnt;
        int mid = (low + high) / 2;
        cnt += mergeSort(arr, low, mid);
        cnt += mergeSort(arr, mid + 1, high);
        cnt += merge(arr, low, mid, high);
        return cnt;
    }
    static long merge(long arr[], int low, int mid, int high) {
        ArrayList<Long> temp = new ArrayList<>();
        int left = low, right = mid + 1;
        long cnt = 0;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
                cnt += (mid - left + 1);
            }
        }
        while (left <= mid) temp.add(arr[left++]);
        while (right <= high) temp.add(arr[right++]);
        for (int i = low; i <= high; i++) arr[i] = temp.get(i - low);
        return cnt;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
