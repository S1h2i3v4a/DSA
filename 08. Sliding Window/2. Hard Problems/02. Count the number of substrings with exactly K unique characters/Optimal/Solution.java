/*
QUESTION:
Given a string `s` and an integer `k`, return the number of substrings with exactly `k` unique characters.

APPROACH:
- Substrings with exactly `k` unique characters = `atMostK(s, k) - atMostK(s, k - 1)`.
- `atMostK(s, k)`: sliding window finding substrings with at most `k` distinct characters:
  - Expand `right`: increment `freq[s.charAt(right) - 'a']`. If new char, increment `distinct`.
  - While `distinct > k`:
    - Decrement `freq[s.charAt(left) - 'a']`. If 0, decrement `distinct`.
    - `left++`.
  - Add `right - left + 1` to total.

TIME COMPLEXITY: O(N) two linear passes.
SPACE COMPLEXITY: O(26) = O(1) auxiliary space.
*/

class Solution {
    private long atMostK(String s, int k) {
        if (k <= 0) return 0;
        int[] freq = new int[26];
        int left = 0, distinct = 0;
        long count = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'a';
            if (freq[idx]++ == 0) distinct++;

            while (distinct > k) {
                int leftIdx = s.charAt(left) - 'a';
                if (--freq[leftIdx] == 0) distinct--;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }

    public long substrCount(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }
}
