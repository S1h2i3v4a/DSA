/*
QUESTION:
Given a string 'str' and an integer 'K', find the length of the largest substring with at most 'K' distinct characters.

EXAMPLE:
For example, if we are given 'str' = "abbbbbbc" and 'K' = 2, the substrings that can be formed are ["abbbbbb", "bbbbbbc"]. Hence, the answer is 7.

APPROACH:
We can use a sliding window approach to solve this problem.

1. Create a function, kDistinctChars, that takes 'k' and the input string 's' as parameters.
   - Initialize an unordered_map, mp, to store the frequency of characters.
   - Initialize 'start' to 0 and 'ans' to 0.
   - Iterate over the string from left to right:
     - Increment the frequency of the current character in the map.
     - If the number of distinct characters in the map exceeds 'k', move the 'start' pointer to the right and remove characters from the map until the number of distinct characters becomes equal to 'k'.
     - Update 'ans' with the maximum length of the substring (i - start + 1).
   - Return 'ans', which represents the length of the largest substring with at most 'k' distinct characters.

COMPLEXITY ANALYSIS:
- Time complexity: O(n), where n is the length of the string 's'. We iterate over the string once using the sliding window approach.
- Space complexity: O(k), as the space used by the unordered_map is proportional to the number of distinct characters, which can be at most 'k'.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.HashMap;
import java.util.Map;

class Longest_Substring_With_K_Uniques {

    public static int longestkSubstr(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = -1;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            if (map.size() == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        System.out.println("Longest Substring with " + k + " Uniques: " + longestkSubstr(s, k));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
