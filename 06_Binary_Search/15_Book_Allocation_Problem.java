/*
 * Problem Name: Book Allocation Problem
 * Platform: GeeksforGeeks
 * Difficulty: Hard
 * Time Complexity: O(N * log(sum - max))
 * Space Complexity: O(1)
 */

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
