/*
QUESTION:
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.

APPROACH:
- Pure recursive combination: if str1[i] == str2[j], include char once and advance both.
- Else branch into taking str1[i] or str2[j], choose the shorter resulting string.

TIME COMPLEXITY: O(2^(M+N)).
SPACE COMPLEXITY: O((M+N)^2) string allocations and stack.
*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        return solve(0, 0, str1, str2);
    }

    private String solve(int i, int j, String s1, String s2) {
        if (i == s1.length()) return s2.substring(j);
        if (j == s2.length()) return s1.substring(i);

        if (s1.charAt(i) == s2.charAt(j)) {
            return s1.charAt(i) + solve(i + 1, j + 1, s1, s2);
        }

        String opt1 = s1.charAt(i) + solve(i + 1, j, s1, s2);
        String opt2 = s2.charAt(j) + solve(i, j + 1, s1, s2);

        return opt1.length() <= opt2.length() ? opt1 : opt2;
    }
}
