/**
 * Problem Name: Longest Substring Without Repeating Characters
 * Platform: LeetCode (3)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) auxiliary space (fixed ASCII array of size 256).
 * 
 * Approach:
 * Dynamic Sliding Window with Character Position Map.
 * Maintain `left` window pointer and `lastIndex` array initialized to -1.
 * For each character at index `right`:
 * - If `s.charAt(right)` was seen previously at `lastIndex[c] >= left`, shift `left = lastIndex[c] + 1`.
 * - Update `lastIndex[c] = right`.
 * - Calculate window size `right - left + 1` and update max length.
 */

import java.util.Arrays;

class Longest_Substring_Without_Repeating_Characters {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);

        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }

            lastIndex[c] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Longest Substring Length for \"" + s + "\": " + lengthOfLongestSubstring(s));
    }
}
