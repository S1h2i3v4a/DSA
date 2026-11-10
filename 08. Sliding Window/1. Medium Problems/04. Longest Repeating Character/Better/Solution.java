/*
QUESTION:
Find the length of the longest substring containing the same letter after at most `k` replacements.

APPROACH:
- Two-pointer sliding window `[left, right]`.
- Maintain frequency array of characters in window and `maxFreq`.
- While `(right - left + 1) - maxFreq > k`:
  - Decrement `count[s.charAt(left) - 'A']` and `left++`.
  - Recalculate `maxFreq` over all 26 characters.
- Update `maxLen = Math.max(maxLen, right - left + 1)`.

TIME COMPLEXITY: O(26 * N) = O(N).
SPACE COMPLEXITY: O(26) = O(1) for count array.
*/

class Solution {
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        int left = 0, maxFreq = 0, maxLen = 0;

        for (int right = 0; right < n; right++) {
            int idx = s.charAt(right) - 'A';
            count[idx]++;
            maxFreq = Math.max(maxFreq, count[idx]);

            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                maxFreq = 0;
                for (int c = 0; c < 26; c++) maxFreq = Math.max(maxFreq, count[c]);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
