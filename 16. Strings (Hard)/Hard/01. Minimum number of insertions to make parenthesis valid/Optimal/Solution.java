/*
QUESTION:
Given a parentheses string s, return minimum number of insertions required to make s valid.

APPROACH:
- Maintain two counters: `open` (unmatched opening brackets) and `add` (insertions needed for closing brackets).
- For '(': open++.
- For ')': if open > 0, open--; else add++.
- Total insertions = open + add.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0, add = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open > 0) open--;
                else add++;
            }
        }
        return add + open;
    }
}
