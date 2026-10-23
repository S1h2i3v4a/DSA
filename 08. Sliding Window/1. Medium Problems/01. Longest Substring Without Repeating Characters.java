/*
Question:
Given a string s, find the length of the longest substring without repeating characters.

Approach:
- We can use a sliding window approach to solve this problem.
- We maintain a window that contains only unique characters.
- We use a hash map to store the frequency of characters in the current window.
- We iterate through the string and expand the window by adding one character at a time.
- If we encounter a repeating character, we shrink the window from the left until the character is no longer in the window.
- We keep track of the maximum window size encountered so far and return it as the result.

Example:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.Arrays;

class Longest_Substring_Without_Repeating_Characters {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);

        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }

            lastIndex[c] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Longest Substring Length for \"" + s + "\": " + lengthOfLongestSubstring(s));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
