/*QUESTION:
Given a string consisting of lowercase English alphabets, the task is to find the number of distinct subsequences of the string. Note that the answer can be very large, so the output will be the answer modulo 10^9 + 7.

Example:
Input: s = "gfg"
Output: 7
Explanation: The seven distinct subsequences are "", "g", "f", "gf", "fg", "gg", and "gfg".

Approach:
To find the number of distinct subsequences, we can use a recursive approach with backtracking.
1. Create a set to store the distinct subsequences.
2. Create a helper function called 'solve' that takes the input string 's', the current index, and a temporary string as parameters.
3. In the 'solve' function:
   - If the current index is equal to the size of the input string 's', insert the temporary string into the set of distinct subsequences.
   - Otherwise, do the following:
     - Include the character at the current index in the temporary string by appending it.
     - Recursively call the 'solve' function with the updated temporary string and the next index.
     - Exclude the character at the current index from the temporary string.
     - Recursively call the 'solve' function with the updated temporary string and the next index.
4. Create the main function called 'distinctSubsequences' that calculates the number of distinct subsequences.
   - Initialize an empty string as the temporary string.
   - Call the 'solve' function with the input string 's', index 0, and the empty temporary string.
   - Return the size of the set of distinct subsequences modulo 10^9 + 7.

Time Complexity: O(2^N), where N is the length of the input string 's'.
Space Complexity: O(2^N), where N is the length of the input string 's' (for storing the distinct subsequences in the set).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    private static Set<String> st;
    private static void solve(String s, int index, StringBuilder temp) {
        if (index == s.length()) {
            st.add(temp.toString());
            return;
        }
        temp.append(s.charAt(index));
        solve(s, index + 1, temp);
        temp.deleteCharAt(temp.length() - 1);
        solve(s, index + 1, temp);
    }
    public static int distinctSubsequences(String s) {
        st = new HashSet<>();
        solve(s, 0, new StringBuilder());
        return (int) (st.size() % 1000000007L);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
