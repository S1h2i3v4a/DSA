/*
QUESTION:-
The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
Given a string s, return the sum of beauty of all of its substrings.

Example:
Input: s = "aabcb"
Output: 5
Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty 1.
*/

/*
APPROACH:-
Check all substrings: For each substring s[i..j], compute character frequencies from scratch, find max and min non-zero frequency, and add (max - min) to sum.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int beautySum(String s) {
        int n = s.length();
        int totalBeauty = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] freq = new int[26];
                for (int k = i; k <= j; k++) {
                    freq[s.charAt(k) - 'a']++;
                }
                int max = 0, min = Integer.MAX_VALUE;
                for (int f : freq) {
                    if (f > 0) {
                        max = Math.max(max, f);
                        min = Math.min(min, f);
                    }
                }
                if (max > min) totalBeauty += (max - min);
            }
        }
        return totalBeauty;
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(26) = O(1)
