/*
QUESTION:
Given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

APPROACH:
- Continuously replace adjacent valid pairs "()", "{}", "[]" with "" until no more replacements can be made.
- If the resulting string is empty, it is valid; otherwise, it is invalid.

TIME COMPLEXITY: O(N^2) because each replacement scans the string of length up to N, up to N/2 times.
SPACE COMPLEXITY: O(N) due to newly created strings on each replacement.
*/

class Solution {
    public boolean isValid(String s) {
        int prevLen = -1;
        while (s.length() != prevLen) {
            prevLen = s.length();
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }
        return s.isEmpty();
    }
}
