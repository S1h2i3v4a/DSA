/*
QUESTION:
A string is called a complete string if every prefix of this string is also present in array A.
Find the longest complete string (lexicographically smallest on tie), or "None".

APPROACH:
- Store all words in a HashSet.
- For each word, check every prefix from length 1 to L-1 using `set.contains()`.
- Update longest lexicographically smallest result.

TIME COMPLEXITY: O(N * L^2) due to substring creations and hash lookups.
SPACE COMPLEXITY: O(N * L) for HashSet.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static String completeString(int n, String[] a) {
        Set<String> set = new HashSet<>();
        for (String s : a) set.add(s);

        String longest = "";

        for (String s : a) {
            boolean valid = true;
            for (int len = 1; len <= s.length(); len++) {
                if (!set.contains(s.substring(0, len))) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                if (s.length() > longest.length()) {
                    longest = s;
                } else if (s.length() == longest.length() && s.compareTo(longest) < 0) {
                    longest = s;
                }
            }
        }

        return longest.isEmpty() ? "None" : longest;
    }
}
