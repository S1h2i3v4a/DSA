/*
QUESTION:
Determine if `s` with '*', '(', ')' is valid using Memoization / DP.

APPROACH:
- Use a 2D Boolean array `memo[n][n]` where state is `(index, openCount)`.
- If open < 0 or open > n, invalid.
- Cache results of subproblems to avoid redundant branches.

TIME COMPLEXITY: O(N^2) states.
SPACE COMPLEXITY: O(N^2) for memoization table.
*/

class Solution {
    private Boolean[][] memo;

    private boolean check(String s, int idx, int count) {
        if (count < 0) return false;
        if (idx == s.length()) return count == 0;
        if (count >= s.length()) return false;

        if (memo[idx][count] != null) return memo[idx][count];

        char c = s.charAt(idx);
        boolean result;
        if (c == '(') {
            result = check(s, idx + 1, count + 1);
        } else if (c == ')') {
            result = check(s, idx + 1, count - 1);
        } else {
            result = check(s, idx + 1, count + 1) ||
                     check(s, idx + 1, count - 1) ||
                     check(s, idx + 1, count);
        }
        return memo[idx][count] = result;
    }

    public boolean checkValidString(String s) {
        memo = new Boolean[s.length()][s.length()];
        return check(s, 0, 0);
    }
}
