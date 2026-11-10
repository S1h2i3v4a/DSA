/*
QUESTION:
Given a string `s` consisting only of characters a, b and c,
return the number of substrings containing at least one occurrence of all these characters a, b and c.

APPROACH:
- Inspect all substrings `s[i...j]`.
- Check if substring contains 'a', 'b', and 'c'.
- If so, increment answer and all further extensions `s[i...k]` (for k >= j) also contain all three, so we can add `n - j` and break.

TIME COMPLEXITY: O(N^2) inspecting substrings.
SPACE COMPLEXITY: O(1) boolean flags for 'a', 'b', 'c'.
*/

class Solution {
    public static int numberOfSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            boolean hasA = false, hasB = false, hasC = false;
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                if (ch == 'a') hasA = true;
                if (ch == 'b') hasB = true;
                if (ch == 'c') hasC = true;

                if (hasA && hasB && hasC) {
                    count += (n - j);
                    break;
                }
            }
        }
        return count;
    }
}
