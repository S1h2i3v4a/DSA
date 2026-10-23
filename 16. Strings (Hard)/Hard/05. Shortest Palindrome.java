/*
QUESTION:
You are given a string s. You can convert s to a palindrome by adding characters in front of it.
Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Approach:
1. We can solve this problem using the KMP (Knuth-Morris-Pratt) algorithm.
2. First, we reverse the string s and store it in the string rev.
3. We concatenate s and rev with a special character '$' in between, creating the string temp.
4. We calculate the LPS (Longest Prefix Suffix) array for the string temp using KMP.
5. The last value in the LPS array, lps.back(), represents the length of the longest prefix of temp that is also a suffix.
6. We extract the substring of s starting from index lps.back() to the end and reverse it.
7. Finally, we concatenate the reversed substring with s and return the result as the shortest palindrome.

Complexity Analysis:
- The time complexity of this solution is O(n), where n is the length of the string s.
- The space complexity is O(n) to store the LPS array.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
