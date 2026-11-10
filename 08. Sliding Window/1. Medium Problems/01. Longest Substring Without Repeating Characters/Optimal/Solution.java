/*
QUESTION:
Given a string `s`, find the length of the longest substring without repeating characters.

APPROACH:
- Use a sliding window with an index map (or array of size 256 initialized to -1).
- Maintain the last seen index of each character.
- When `s.charAt(right)` was seen at index `lastIdx >= left`, jump `left` directly to `lastIdx + 1`.
- Update the last seen position of `s.charAt(right)` to `right`.
- Max length is `Math.max(maxLen, right - left + 1)`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(min(N, 256)) for the index mapping.
*/

import java.util.Arrays;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int left = 0;
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }
            lastIndex[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
