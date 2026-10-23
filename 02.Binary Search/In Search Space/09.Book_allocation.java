/*
Question:
You have N books, each with Ai number of pages. M students need to be allocated contiguous books, with each student getting at least one book. Out of all the permutations, the goal is to find the permutation where the student with the most pages allocated to him gets the minimum number of pages, out of all possible permutations.

Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order.

Example:

Input:
N = 4
A[] = {12,34,67,90}
M = 2
Output: 113
Explanation:
Allocation can be done in following ways:
{12} and {34, 67, 90} Maximum Pages = 191
{12, 34} and {67, 90} Maximum Pages = 157
{12, 34, 67} and {90} Maximum Pages = 113.
Therefore, the minimum of these cases is 113, which is selected as the output.

Approach:
- We can use binary search to find the minimum number of pages that can be allocated to the student with the most pages.
- The range for binary search will be from the minimum number of pages in the array to the sum of all the pages in the array.
- For each mid value, we will check if it is possible to allocate the books in a way that the maximum number of pages for a student is less than or equal to mid.
- To check this, we iterate through the array of pages and keep track of the number of students and the sum of pages allocated to the current student.
- If at any point, the sum exceeds the mid value, we increment the number of students and reset the sum to the current page value.
- If the number of students exceeds the given M, it means we need more students to allocate the pages, so we return false.
- If at the end, the number of students is less than or equal to M, it means it is possible to allocate the pages within the given constraints, so we return true.
- Finally, we perform binary search and find the minimum mid value that returns true in the isPossible function.

Code:
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
        int students = 1, pagesStudent = 0;
        for (int pages : arr) {
            if (pagesStudent + pages > maxPages) {
                students++;
                pagesStudent = pages;
            } else {
                pagesStudent += pages;
            }
        }
        return students;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
