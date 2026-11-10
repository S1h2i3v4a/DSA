/*
QUESTION:
Find the length of the longest substring containing the same letter after at most `k` replacements in O(N).

APPROACH:
- Non-shrinking sliding window.
- We don't need to decrement `maxFreq` when sliding `left`, because we are only looking for windows with a strictly larger `maxFreq` to achieve a greater maximum length.
- If `(right - left + 1) - maxFreq > k`:
  - Decrement count of `s.charAt(left)` and `left++`.
- Return `s.length() - left`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(26) = O(1) auxiliary space.
*/

class Solution {
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxFreq = 0, right = 0;

        for (right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            count[idx]++;
            maxFreq = Math.max(maxFreq, count[idx]);

            if ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
        }
        return right - left;
    }
}
