/*
QUESTION:
Convert string s to a palindrome by adding minimum characters in front of it.

APPROACH:
- KMP Algorithm:
- Form temp = s + '#' + reverse(s).
- Compute LPS array for temp.
- `lps[temp.length() - 1]` is the length of the longest palindromic prefix of s.
- Reverse remaining suffix and prepend to s.

TIME COMPLEXITY: O(N) linear time.
SPACE COMPLEXITY: O(N) for LPS array and temp string.
*/

class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String temp = s + "#" + rev;
        int n = temp.length();
        int[] lps = new int[n];

        for (int i = 1; i < n; i++) {
            int j = lps[i - 1];
            while (j > 0 && temp.charAt(i) != temp.charAt(j)) {
                j = lps[j - 1];
            }
            if (temp.charAt(i) == temp.charAt(j)) j++;
            lps[i] = j;
        }

        int addLen = s.length() - lps[n - 1];
        return rev.substring(0, addLen) + s;
    }
}
