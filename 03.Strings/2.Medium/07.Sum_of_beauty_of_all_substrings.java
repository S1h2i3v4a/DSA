/*Question:

The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.

Given a string `s`, you need to calculate the sum of beauty for all of its substrings. The beauty of a substring is defined as the difference between the highest and lowest frequency of any character in the substring.

Write a function `beautySum` that takes a string `s` as input and returns the sum of beauty for all substrings.

Example:

Input: s = "aabcb"
Output: 5
Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.

Input: s = "aabcbaa"
Output: 17

Approach:

1. Initialize a variable `ans` to store the total beauty sum.
2. Iterate over the string `s` with the first loop, starting from index `i`.
   - Initialize a frequency array `freq` of size 26, initialized with zeros.
   - Iterate over the string `s` with the second loop, starting from index `j` equal to `i`.
      - Increment the frequency of the character `s[j]` in the `freq` array.
      - Calculate the difference between the highest and lowest frequencies in the `freq` array and add it to `ans`.
3. Return the value of `ans` as the final result.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int beautySum(String s) {
        int totalBeauty = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                int maxFreq = 0, minFreq = Integer.MAX_VALUE;
                for (int count : freq) {
                    if (count > 0) {
                        maxFreq = Math.max(maxFreq, count);
                        minFreq = Math.min(minFreq, count);
                    }
                }
                totalBeauty += (maxFreq - minFreq);
            }
        }
        return totalBeauty;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
