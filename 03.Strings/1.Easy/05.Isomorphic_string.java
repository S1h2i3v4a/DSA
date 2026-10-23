/*
Question:
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.

Approach:
1. Initialize two maps to store the mapping of characters from s to t and from t to s.
2. Iterate through each character in s and t simultaneously.
3. If the current characters in s and t are already mapped differently, return false.
4. If the current characters in s and t are not mapped yet, add them to the maps.
5. If the current characters in s and t are already mapped to each other, continue to the next characters.
6. If all characters have been iterated and no inconsistencies are found, return true.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
