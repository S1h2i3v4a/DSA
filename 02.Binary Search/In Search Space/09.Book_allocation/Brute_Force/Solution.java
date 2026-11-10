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
Linear Search: Range of allocated maximum pages is [max(arr), sum(arr)]. Try every value p from max(arr) to sum(arr) sequentially until countStudents(p) <= k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findPages(int[] arr, int k) {
        if (k > arr.length) return -1;
        int maxP = 0, sumP = 0;
        for (int pages : arr) {
            maxP = Math.max(maxP, pages);
            sumP += pages;
        }

        for (int p = maxP; p <= sumP; p++) {
            if (countStudents(arr, p) <= k) return p;
        }
        return -1;
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

// TIME COMPLEXITY = O((sum - max) * N)
// SPACE COMPLEXITY = O(1)
