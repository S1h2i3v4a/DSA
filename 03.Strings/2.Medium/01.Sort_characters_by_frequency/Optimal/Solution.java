/*
QUESTION:-
Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
Return the sorted string. If there are multiple answers, return any of them.

Example:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
*/

/*
APPROACH:-
Bucket Sort: Count frequency of each character. Create buckets where index represents frequency. Place characters into their respective frequency buckets. Traverse buckets from highest frequency to lowest to build the answer.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        List<Character>[] buckets = new List[s.length() + 1];
        for (char c : counts.keySet()) {
            int freq = counts.get(c);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i > 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
