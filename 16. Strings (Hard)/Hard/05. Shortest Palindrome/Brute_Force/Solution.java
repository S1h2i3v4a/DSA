/*
QUESTION:
Convert string s to a palindrome by adding minimum characters in front of it.

APPROACH:
- Find longest palindromic prefix by checking if s[0..i] equals reverse(s[0..i]) from i = n - 1 down to 0.
- Prefix characters from i + 1 to n in reverse order to the front of s.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) string creation.
*/

class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        String rev = new StringBuilder(s).reverse().toString();

        for (int i = 0; i < n; i++) {
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }

        return "";
    }
}
