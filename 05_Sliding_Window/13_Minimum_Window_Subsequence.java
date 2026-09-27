/**
 * Problem Name: Minimum Window Subsequence
 * Platform: LeetCode (727) / GeeksforGeeks
 * Difficulty: Hard
 * 
 * Time Complexity: O(N * M) where N is s1 length and M is s2 length.
 * Space Complexity: O(1) auxiliary space using Two Pointers.
 * 
 * Approach:
 * Two Pointers Forward & Reverse Matching.
 * 1. Forward Pass: Traverse `s1` with pointer `i` and `s2` with pointer `j`. Match characters `s1[i] == s2[j]`.
 * 2. When `j == s2.length()`, full subsequence `s2` is matched in `s1`.
 * 3. Reverse Pass: Move `j` backwards from `M-1` down to `0` and shrink `i` backwards to find the exact starting index `left`.
 * 4. Update minimum window substring `minWindow` if `(end - left + 1) < minLen`.
 * 5. Resume forward search setting `i = left + 1` and `j = 0`.
 */

class Minimum_Window_Subsequence {

    public static String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int i = 0;

        int minLen = Integer.MAX_VALUE;
        String minSub = "";

        while (i < n) {
            int j = 0;

            // Forward Pass
            while (i < n) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    j++;
                    if (j == m) break;
                }
                i++;
            }

            if (j < m) break; // Could not match full s2

            // Reverse Pass to shrink window
            int end = i;
            j = m - 1;
            while (i >= 0) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    j--;
                    if (j < 0) break;
                }
                i--;
            }

            int len = end - i + 1;
            if (len < minLen) {
                minLen = len;
                minSub = s1.substring(i, end + 1);
            }

            // Resume forward pass from i + 1
            i = i + 1;
        }

        return minSub;
    }

    public static void main(String[] args) {
        String s1 = "abcdebdde";
        String s2 = "bde";
        System.out.println("Minimum Window Subsequence: \"" + minWindow(s1, s2) + "\"");
    }
}
