/*
QUESTION:-
Given an array arr[] of integers of length n, where arr[i] represents the number of pages in the i-th book, and an integer k representing the number of students.
Allocate books in such a way that the maximum number of pages assigned to a student is minimized.

Example:
Input: arr = [12, 34, 67, 90], k = 2
Output: 113
*/

/*
APPROACH:-
Binary Search on Answer: The answer lies in [max(arr), sum(arr)]. Use binary search: if countStudents(mid) <= k, record ans = mid and high = mid - 1; else low = mid + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findPages(int[] arr, int k) {
        if (k > arr.length) return -1;
        int low = 0, high = 0;
        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countStudents(arr, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int countStudents(int[] arr, int maxPages) {
        int students = 1, pagesAllocated = 0;
        for (int pages : arr) {
            if (pagesAllocated + pages > maxPages) {
                students++;
                pagesAllocated = pages;
            } else {
                pagesAllocated += pages;
            }
        }
        return students;
    }
}

// TIME COMPLEXITY = O(N * log(sum - max))
// SPACE COMPLEXITY = O(1)
