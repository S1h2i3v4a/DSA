/*
QUESTION:
You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character at most `k` times.
Return the length of the longest substring containing the same letter you can get after performing the operations.

APPROACH:
- Inspect all substrings `s[i...j]`.
- Count character frequencies in substring, finding `maxFreq`.
- If `(j - i + 1) - maxFreq <= k`, valid substring can be formed.
- Track maximum length.

TIME COMPLEXITY: O(N^2) inspecting all substrings.
SPACE COMPLEXITY: O(26) for frequency counting.
*/

class Solution {
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int[] count = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'A';
                count[idx]++;
                maxFreq = Math.max(maxFreq, count[idx]);
                int len = j - i + 1;
                if (len - maxFreq <= k) {
                    maxLen = Math.max(maxLen, len);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }
}
