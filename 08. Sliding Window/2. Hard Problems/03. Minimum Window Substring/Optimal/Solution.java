/*
QUESTION:
Given two strings `s` and `t`, return the minimum window substring of `s` containing all characters of `t` in O(M + N).

APPROACH:
- Two-pointer sliding window `[left, right]` with frequency map / array.
- Store character counts of `t` in `tCount`.
- Track `matched`: number of characters from `t` currently satisfied in window.
- Expand `right`:
  - If `s[right]` is in `t` and `windowCount[s[right]] < tCount[s[right]]`, increment `matched`.
  - Increment `windowCount[s[right]]`.
- While `matched == t.length()`:
  - Update `minLen` and `startIdx`.
  - Try to shrink `left`: decrement `windowCount[s[left]]`.
  - If `windowCount[s[left]] < tCount[s[left]]`, decrement `matched`.
  - `left++`.

TIME COMPLEXITY: O(M + N) where M is length of s and N is length of t.
SPACE COMPLEXITY: O(256) = O(1) for character count arrays.
*/

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        int[] tCount = new int[256];
        for (char c : t.toCharArray()) tCount[c]++;

        int[] windowCount = new int[256];
        int left = 0, matched = 0;
        int minLen = Integer.MAX_VALUE;
        int startIdx = -1;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (tCount[rightChar] > 0) {
                if (windowCount[rightChar] < tCount[rightChar]) {
                    matched++;
                }
            }
            windowCount[rightChar]++;

            while (matched == t.length()) {
                int len = right - left + 1;
                if (len < minLen) {
                    minLen = len;
                    startIdx = left;
                }

                char leftChar = s.charAt(left);
                windowCount[leftChar]--;
                if (tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar]) {
                    matched--;
                }
                left++;
            }
        }

        return (startIdx == -1) ? "" : s.substring(startIdx, startIdx + minLen);
    }
}
