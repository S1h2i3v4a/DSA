/*
QUESTION:
Given a string `s` containing '(', ')' and '*', return true if `s` is valid.
'*' could be treated as a single right parenthesis ')', left parenthesis '(', or an empty string "".

APPROACH:
- Recursive backtracking: at each '*', branch into 3 possibilities: '(', ')', or "".
- Track open bracket count. If open < 0 at any point, prune branch.
- Valid if open count reaches 0 at end of string.

TIME COMPLEXITY: O(3^N) exponential branching in worst case.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private boolean check(String s, int idx, int count) {
        if (count < 0) return false;
        if (idx == s.length()) return count == 0;

        char c = s.charAt(idx);
        if (c == '(') {
            return check(s, idx + 1, count + 1);
        } else if (c == ')') {
            return check(s, idx + 1, count - 1);
        } else {
            return check(s, idx + 1, count + 1) ||
                   check(s, idx + 1, count - 1) ||
                   check(s, idx + 1, count);
        }
    }

    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }
}
