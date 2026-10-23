/*QUESTION:
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

APPROACH:
1. Create an unordered_set ltrs to store all unique letters in the given string s.
2. Initialize a variable ans to keep track of the maximum length of the substring.
3. Iterate through each letter ltr in the set ltrs:
   - Initialize variables start and ltrCnt to track the starting index of the current substring and the count of ltr in the substring.
   - Iterate through each character in the string s:
     - If the character is equal to ltr, increment ltrCnt.
     - Enter a while loop to adjust the start index until the number of characters in the substring that are different from ltr is more than k.
     - During the adjustment, if the character at the start index is equal to ltr, decrement ltrCnt.
     - Increment the start index.
     - If the length of the current substring minus ltrCnt is at most k, update ans by taking the maximum of ans and the length of the current substring.
4. Return the maximum length of the substring (ans).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Longest_Repeating_Character_Replacement {

    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;
            maxFreq = Math.max(maxFreq, count[c - 'A']);

            while ((right - left + 1 - maxFreq) > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println("Longest Repeating Character Replacement: " + characterReplacement(s, k));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
