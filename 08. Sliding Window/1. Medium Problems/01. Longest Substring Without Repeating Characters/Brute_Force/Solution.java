/*
QUESTION:
Given a string `s`, find the length of the longest substring without repeating characters.

APPROACH:
- Inspect all possible substrings `s[i...j]` using two nested loops.
- Use a boolean array or HashSet to check if all characters in the current substring are unique.
- Track the maximum length found.

TIME COMPLEXITY: O(N^2) checking all substrings.
SPACE COMPLEXITY: O(min(N, 256)) for the character set.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (seen.contains(c)) break;
                seen.add(c);
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
