/**
 * Problem Name: Minimum Insertion Steps to Make a String Palindrome
 * Platform: LeetCode (LC 1312) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(N^2) where N is the length of string s
 * Space Complexity: O(N) using space-optimized LCS
 *
 * Approach:
 *   - To turn a string `s` into a palindrome with the minimum number of insertions:
 *     1. Identify the longest sequence of characters in `s` that is already a palindrome:
 *        LPS(s) = LCS(s, reverse(s)).
 *     2. The remaining characters (length - LPS) lack a matching symmetric counterpart.
 *     3. Therefore, we must insert exactly one matching character for each of these missing positions.
 *     4. Minimum Insertions = length(s) - LPS(s).
 */

class MinimumInsertionsToMakeStringPalindrome {

    public static int minInsertions(String s) {
        int n = s.length();
        int lps = longestPalindromeSubseq(s);
        return n - lps;
    }

    private static int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == reversed.charAt(j - 1)) {
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
        // s = "zzazz" -> Expected: 0 (Already a palindrome)
        System.out.println("Test 1 (\"zzazz\"): " + minInsertions("zzazz")); // 0

        // Test Case 2:
        // s = "mbadm" -> Expected: 2 (Insert 'b' and 'd' -> "mbdadbm")
        System.out.println("Test 2 (\"mbadm\"): " + minInsertions("mbadm")); // 2

        // Test Case 3:
        // s = "leetcode" -> Expected: 5
        System.out.println("Test 3 (\"leetcode\"): " + minInsertions("leetcode")); // 5
    }
}
