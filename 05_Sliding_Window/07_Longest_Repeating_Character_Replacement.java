/**
 * Problem Name: Longest Repeating Character Replacement
 * Platform: LeetCode (424)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) fixed 26-element array.
 * 
 * Approach:
 * Sliding Window with Max Frequency Tracking.
 * Track frequency of characters in current window `[left, right]` and `maxFreq` (count of most frequent character).
 * Condition for valid window: `(windowSize - maxFreq) <= k`.
 * If `(right - left + 1 - maxFreq) > k`, shrink window by decrementing `count[s.charAt(left) - 'A']` and incrementing `left`.
 */

class Longest_Repeating_Character_Replacement {

    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;
            maxFreq = Math.max(maxFreq, count[c - 'A']);

            while ((right - left + 1 - maxFreq) > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement(s, k));
    }
}
