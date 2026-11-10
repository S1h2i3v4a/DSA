/*
QUESTION:
Find size of the longest substring with at most `k` unique characters in O(N) single pass.

APPROACH:
- Non-shrinking sliding window.
- Expand `right`: increment frequency of `s.charAt(right)`.
- If `map.size() > k`:
  - Shrink window by at most 1: decrement frequency of `s.charAt(left)`, remove if 0, `left++`.
- Window size never decreases, preserving max length.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(K) for frequency map.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int longestkSubstr(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;

        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
        }
        return right - left;
    }
}
