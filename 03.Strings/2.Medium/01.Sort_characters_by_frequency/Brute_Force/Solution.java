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
Frequency Map and Max-Heap / PriorityQueue: Count character frequencies in a HashMap. Push all characters into a PriorityQueue ordered by frequency in descending order. Poll and construct result.
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

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> counts.get(b) - counts.get(a));
        pq.addAll(counts.keySet());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            char c = pq.poll();
            int count = counts.get(c);
            for (int i = 0; i < count; i++) sb.append(c);
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N + K log K) where K is unique characters
// SPACE COMPLEXITY = O(N)
