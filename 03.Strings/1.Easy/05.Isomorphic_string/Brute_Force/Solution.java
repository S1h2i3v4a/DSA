/*
QUESTION:-
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example:
Input: s = "egg", t = "add"
Output: true
*/

/*
APPROACH:-
Two HashMaps: Maintain two maps, mapST for mapping chars from s to t and mapTS for mapping chars from t to s. If any mapping conflict is detected, return false.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (mapST.containsKey(c1) && mapST.get(c1) != c2) return false;
            if (mapTS.containsKey(c2) && mapTS.get(c2) != c1) return false;
            mapST.put(c1, c2);
            mapTS.put(c2, c1);
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
