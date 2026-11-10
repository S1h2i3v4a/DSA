/*
QUESTION:
Find size of the longest substring with at most `k` unique characters.

APPROACH:
- Two-pointer sliding window with HashMap.
- Expand `right`: increment frequency of `s.charAt(right)`.
- While `map.size() > k`:
  - Decrement frequency of `s.charAt(left)`. If 0, remove.
  - `left++`.
- Update `maxLen = Math.max(maxLen, right - left + 1)`.

TIME COMPLEXITY: O(2 * N) = O(N).
SPACE COMPLEXITY: O(K) for map storage.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int longestkSubstr(String s, int k) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = -1;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
