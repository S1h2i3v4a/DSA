/*
QUESTION:
Return the number of nice sub-arrays (subarrays with `k` odd numbers).

APPROACH:
- Treat odd numbers as 1 and even numbers as 0.
- The problem reduces to finding subarrays with sum equal to `k`.
- Use a HashMap to store prefix odd count frequencies.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(N) for prefix map.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int oddCount = 0, ans = 0;

        for (int num : nums) {
            if (num % 2 != 0) oddCount++;
            if (map.containsKey(oddCount - k)) {
                ans += map.get(oddCount - k);
            }
            map.put(oddCount, map.getOrDefault(oddCount, 0) + 1);
        }
        return ans;
    }
}
