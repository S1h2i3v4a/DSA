/**
 * Problem Name: Delete Operation for Two Strings (Minimum Insertions & Deletions to Transform)
 * Platform: LeetCode (LC 583) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) where M = word1.length(), N = word2.length()
 * Space Complexity: O(N) using space-optimized LCS
 *
 * Approach:
 *   - Given two strings `word1` and `word2`, find the minimum number of deletions to make them equal.
 *   - The longest common component that should never be deleted is their Longest Common Subsequence (LCS).
 *   - To reach the LCS from both strings:
 *     - We must delete (word1.length() - LCS) characters from word1.
 *     - We must delete (word2.length() - LCS) characters from word2.
 *   - Total operations = (M - LCS) + (N - LCS) = M + N - 2 * LCS.
 *   - (This also directly solves the GFG problem "Minimum Deletions and Insertions to Convert String A to B").
 */

class DeleteOperationForTwoStrings {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int lcs = lcsSpaceOptimized(word1, word2);

        return (m - lcs) + (n - lcs);
    }

    private static int lcsSpaceOptimized(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
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
        // word1 = "sea", word2 = "eat" -> LCS = "ea" (len 2) -> (3-2) + (3-2) = 2
        System.out.println("Test 1 (\"sea\", \"eat\"): " + minDistance("sea", "eat")); // 2

        // Test Case 2:
        // word1 = "leetcode", word2 = "etco" -> LCS = "etco" (len 4) -> (8-4) + (4-4) = 4
        System.out.println("Test 2 (\"leetcode\", \"etco\"): " + minDistance("leetcode", "etco")); // 4
    }
}
