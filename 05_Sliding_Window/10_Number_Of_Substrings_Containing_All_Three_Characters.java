/**
 * Problem Name: Number of Substrings Containing All Three Characters
 * Platform: LeetCode (1358)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) auxiliary array of size 3.
 * 
 * Approach:
 * Last Seen Index Strategy.
 * Maintain `lastSeen` array initialized to -1 for characters 'a', 'b', and 'c'.
 * As we iterate `i` from `0` to `N-1`:
 * - Update `lastSeen[s.charAt(i) - 'a'] = i`.
 * - If all three characters are present in prefix `s[0..i]`, any valid substring ending at `i` can start at any index from `0` up to `min(lastSeen[0], lastSeen[1], lastSeen[2])`.
 * - Increment total count by `1 + min(lastSeen[0], lastSeen[1], lastSeen[2])`.
 */

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
