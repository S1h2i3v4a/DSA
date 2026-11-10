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
Sliding Window: Exact(k) = AtMost(k) - AtMost(k - 1). For atMost(k), expand right pointer and shrink left pointer whenever distinct character count exceeds k.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    long substrCount(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }

    private long atMostK(String s, int k) {
        if (k < 0) return 0;
        int[] freq = new int[26];
        int distinct = 0, left = 0;
        long count = 0;

        for (int right = 0; right < s.length(); right++) {
            int rIdx = s.charAt(right) - 'a';
            if (freq[rIdx] == 0) distinct++;
            freq[rIdx]++;

            while (distinct > k) {
                int lIdx = s.charAt(left) - 'a';
                freq[lIdx]--;
                if (freq[lIdx] == 0) distinct--;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(26) = O(1)
