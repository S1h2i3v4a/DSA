/*
 * Problem Name: Kth Missing Positive Number
 * Platform: LeetCode (1539) / GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(log N)
 * Space Complexity: O(1)
 */

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }
}
