/*
 * Problem Name: Sort an Array of 0s, 1s and 2s (Sort Colors)
 * Platform: LeetCode (75) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}
