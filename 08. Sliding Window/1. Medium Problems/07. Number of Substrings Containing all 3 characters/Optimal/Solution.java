/*
QUESTION:
Given a string `s` consisting only of characters a, b and c,
return the number of substrings containing at least one occurrence of all three characters.

APPROACH:
- Track the last seen index of 'a', 'b', and 'c' initialized to -1.
- As we iterate with `i` from 0 to `n - 1`:
  - Update the last seen position of `s.charAt(i)`.
  - If all three characters have been seen, any substring starting at index `0` up to `min(lastA, lastB, lastC)` and ending at `i` is valid.
  - Number of such substrings ending at `i` is `1 + min(lastA, lastB, lastC)`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int numberOfSubstrings(String s) {
        int[] lastSeen = {-1, -1, -1};
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                int minIdx = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
                count += (1 + minIdx);
            }
        }
        return count;
    }
}
