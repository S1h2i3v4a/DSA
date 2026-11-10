/*
QUESTION:
Longest Increasing Subsequence (LIS).

APPROACH:
- Binary Search with Patience Sorting:
- Maintain `tails` array where tails[i] stores smallest tail of all increasing subsequences of length i+1.
- For each x in nums, binary search for x in tails and insert/replace.

TIME COMPLEXITY: O(N log N).
SPACE COMPLEXITY: O(N).
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int x : nums) {
            int idx = Collections.binarySearch(tails, x);
            if (idx < 0) idx = -(idx + 1);

            if (idx == tails.size()) {
                tails.add(x);
            } else {
                tails.set(idx, x);
            }
        }

        return tails.size();
    }
}
