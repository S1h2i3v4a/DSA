/**
 * Problem Name: Longest Palindromic Subsequence (LPS)
 * Platform: LeetCode (LC 516) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N = s.length()
 * Space Complexity:
 *   - Tabulation: O(N^2) table
 *   - Space-Optimized DP: O(N) 1D array
 *
 * Approach:
 *   - A palindrome reads identically forward and backward.
 *   - Key Reduction:
 *     The Longest Palindromic Subsequence (LPS) of a string `s` is mathematically equivalent
 *     to the Longest Common Subsequence (LCS) between string `s` and its reverse `reverse(s)`.
 *   - Let s1 = s, s2 = reverse(s).
 *     lps(s) = lcs(s1, s2).
 *   - We provide:
 *     1. Bottom-Up LCS Reduction with Tabulation
 *     2. Space-Optimized DP using O(N) auxiliary space.
 */

class LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return lcsSpaceOptimized(s, reversed);
    }

    private static int lcsSpaceOptimized(String s1, String s2) {
        int n = s1.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }

        return prev[n];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s = "bbbab" -> Expected: 4 ("bbbb")
        String s1 = "bbbab";
        System.out.println("Test 1 (\"bbbab\"): " + longestPalindromeSubseq(s1)); // 4

        // Test Case 2:
        // s = "cbbd" -> Expected: 2 ("bb")
        String s2 = "cbbd";
        System.out.println("Test 2 (\"cbbd\"): " + longestPalindromeSubseq(s2)); // 2
    }
}
