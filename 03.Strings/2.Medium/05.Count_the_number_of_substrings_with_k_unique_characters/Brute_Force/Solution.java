/*
QUESTION:-
Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that have exactly k distinct characters.

Example:
Input: S = "aba", K = 2
Output: 3
Explanation: Substrings with 2 distinct characters are "ab", "ba", "aba".
*/

/*
APPROACH:-
Nested Loops: Check all substrings starting from index i. Maintain a boolean visited array to count distinct characters. When distinct count equals k, increment answer; if it exceeds k, break.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    long substrCount(String s, int k) {
        long count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int distinct = 0;
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (freq[idx] == 0) distinct++;
                freq[idx]++;
                if (distinct == k) count++;
                else if (distinct > k) break;
            }
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(26) = O(1)
