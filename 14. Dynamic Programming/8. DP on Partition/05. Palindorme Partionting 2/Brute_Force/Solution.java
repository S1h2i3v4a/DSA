/*
QUESTION:
Given a string s, partition s such that every substring of the partition is a palindrome.
Return minimum cuts needed.

APPROACH:
- Pure recursion: partition at index j from i to n-1 if substring s[i..j] is a palindrome.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int minCut(String s) {
        return solve(0, s) - 1;
    }

    private int solve(int i, String s) {
        if (i == s.length()) return 0;
        int minCost = Integer.MAX_VALUE;

        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(i, j, s)) {
                int cost = 1 + solve(j + 1, s);
                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }

    private boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
