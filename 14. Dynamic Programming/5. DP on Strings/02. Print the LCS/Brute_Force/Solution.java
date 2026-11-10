/*
QUESTION:
Given two strings s1 and s2, find and return any longest common subsequence string.

APPROACH:
- Generate all subsequences of s1, check if they are subsequences of s2, return the longest.

TIME COMPLEXITY: O(2^M * N).
SPACE COMPLEXITY: O(2^M * M) storing subsequences.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String findLCS(int n, int m, String s1, String s2) {
        List<String> subs = new ArrayList<>();
        generate(0, s1, new StringBuilder(), subs);

        String best = "";
        for (String sub : subs) {
            if (isSubsequence(sub, s2) && sub.length() > best.length()) {
                best = sub;
            }
        }
        return best;
    }

    private void generate(int i, String s, StringBuilder cur, List<String> subs) {
        if (i == s.length()) {
            subs.add(cur.toString());
            return;
        }
        cur.append(s.charAt(i));
        generate(i + 1, s, cur, subs);
        cur.deleteCharAt(cur.length() - 1);
        generate(i + 1, s, cur, subs);
    }

    private boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == s.length();
    }
}
