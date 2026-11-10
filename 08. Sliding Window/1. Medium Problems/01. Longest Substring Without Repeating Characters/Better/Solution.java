/*
QUESTION:
Given a string `s`, find the length of the longest substring without repeating characters.

APPROACH:
- Use a two-pointer sliding window `[left, right]` and a HashSet.
- Expand `right`:
  - If `s.charAt(right)` is already in the set, shrink `left` by removing `s.charAt(left++)` until duplicate is gone.
  - Add `s.charAt(right)` to set.
  - Update `maxLen = Math.max(maxLen, right - left + 1)`.

TIME COMPLEXITY: O(2 * N) = O(N) since each character is visited at most twice (by left and right).
SPACE COMPLEXITY: O(min(N, 256)) for HashSet.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int left = 0;
        Set<Character> seen = new HashSet<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            while (seen.contains(c)) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
