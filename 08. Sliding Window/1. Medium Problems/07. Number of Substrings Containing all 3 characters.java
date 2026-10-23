/*
QUESTION:
Given a string s consisting only of characters 'a', 'b', and 'c', you need to count the number of substrings that contain at least one occurrence of all these characters.

APPROACH:
To count the number of substrings with at least one occurrence of 'a', 'b', and 'c', we can use a sliding window approach.

1. Create a helper function, countOfUnvalidSubstrings, which counts the number of substrings that do not contain all three characters.
   - Initialize an unordered_map, mp, to keep track of the counts of characters in the current window.
   - Initialize start to 0 and ans to 0.
   - Iterate over the string from left to right:
     - Increment the count of the current character in mp.
     - If the number of unique characters in mp exceeds 2, it means we have found an invalid substring.
       - Decrement the count of the character at the start of the window in mp.
       - If the count becomes zero, remove the character from mp.
       - Move the start of the window to the next position.
     - Add the length of the current window to ans.
   - Return ans.

2. In the main function, numberOfSubstrings:
   - Calculate the total number of substrings, cntOfTotalSubstrings, using the formula (n * (n + 1)) / 2, where n is the length of the string.
   - Subtract the count of invalid substrings obtained from countOfUnvalidSubstrings from cntOfTotalSubstrings.
   - Return the resulting count.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.Arrays;

class Number_Of_Substrings_Containing_All_Three_Characters {

    public static int numberOfSubstrings(String s) {
        int[] lastSeen = new int[]{-1, -1, -1};
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;

            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                int minIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
                count += 1 + minIndex;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println("Substrings containing all three characters: " + numberOfSubstrings(s));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
