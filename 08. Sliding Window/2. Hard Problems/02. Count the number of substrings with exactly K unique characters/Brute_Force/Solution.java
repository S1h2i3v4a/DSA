/*
QUESTION:
Given a string `s` and an integer `k`, return the number of substrings with exactly `k` unique characters.

APPROACH:
- Inspect all substrings `s[i...j]` using nested loops.
- Track distinct characters in a frequency array.
- If distinct count == k, increment total.
- If distinct count > k, break inner loop.

TIME COMPLEXITY: O(N^2) inspecting all substrings.
SPACE COMPLEXITY: O(26) for frequency counting.
*/

class Solution {
    public long substrCount(String s, int k) {
        int n = s.length();
        long total = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int distinct = 0;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (freq[idx] == 0) distinct++;
                freq[idx]++;

                if (distinct == k) total++;
                else if (distinct > k) break;
            }
        }
        return total;
    }
}
