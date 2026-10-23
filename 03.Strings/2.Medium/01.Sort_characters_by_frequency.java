/*
Question:
Given a string s, sort it in decreasing order based on the frequency of the characters.
The frequency of a character is the number of times it appears in the string.

Approach:
1. Create a frequency map to count the occurrences of each character in the string.
2. Use a priority queue to sort the characters based on their frequencies in decreasing order.
3. Iterate through the priority queue and append the characters to a new string according to their frequencies.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) counts.put(c, counts.getOrDefault(c, 0) + 1);
        List<Character> chars = new ArrayList<>(counts.keySet());
        chars.sort((a, b) -> counts.get(b) - counts.get(a));
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            int count = counts.get(c);
            for (int i = 0; i < count; i++) sb.append(c);
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
