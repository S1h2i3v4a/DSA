/*
QUESTION:
Given a string `s`, you need to print the size of the longest possible substring that has at most `k` unique characters.
If no such substring exists, return -1.

APPROACH:
- Inspect all substrings `s[i...j]` using two nested loops.
- Count distinct characters using a HashSet.
- If `set.size() <= k`, update `maxLen = Math.max(maxLen, j - i + 1)`.

TIME COMPLEXITY: O(N^2) inspecting all substrings.
SPACE COMPLEXITY: O(K) for HashSet.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int longestkSubstr(String s, int k) {
        int n = s.length();
        int maxLen = -1;

        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));
                if (set.size() <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }
}
