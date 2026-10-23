/*
QUESTION:
Given two strings 's' and 't' of lengths 'm' and 'n' respectively, find the minimum window substring of 's' that contains all the characters of 't' (including duplicates). If there is no such substring, return an empty string.

EXAMPLE:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string 't'.

APPROACH:
We can solve this problem using a sliding window approach.

1. Create a function, minWindow, that takes 's' and 't' as parameters.
   - Initialize an unordered_map, mp, to store the frequency of characters in 't'.
   - Iterate over 't' and update the frequencies in the map.
   - Initialize 'count' to the number of unique characters in 't'.
   - Initialize 'start' to 0 and 'minlen' to INT_MAX.
   - Initialize 'substrBegin' to 0, which represents the starting index of the minimum window substring.
   - Iterate over 's' from left to right:
     - Decrement the frequency of the current character in the map.
     - If the frequency becomes 0, decrement 'count'.
     - Check if 'count' becomes 0, indicating that we have found a window containing all characters of 't'.
       - If the current window length is smaller than 'minlen', update 'minlen' and 'substrBegin' to the current window length and starting index respectively.
       - Move the 'start' pointer to the right, increment the frequency of the character at 'start' in the map, and increment 'count' if the frequency becomes greater than 0.
   - Return the minimum window substring by using the substr function on 's' with 'substrBegin' and 'minlen' as parameters.
   - If 'minlen' is still INT_MAX, return an empty string.

COMPLEXITY ANALYSIS:
- Time complexity: O(m + n), where m is the length of 's' and n is the length of 't'. We iterate over both strings once using the sliding window approach.
- Space complexity: O(n), as the space used by the unordered_map is proportional to the number of unique characters in 't', which can be at most 'n'.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
        int required = dictT.size();
        int l = 0, r = 0;
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        int[] ans = {-1, 0, 0};
        while (r < s.length()) {
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            while (l <= r && formed == required) {
                c = s.charAt(l);
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
