/*
QUESTION:
Check whether it is possible to divide `nums` into sets of `k` consecutive numbers in O(N log N).

APPROACH:
- Use a `TreeMap<Integer, Integer>` to store frequencies of numbers in sorted order.
- While the map is non-empty:
  - Pick the smallest key `first`.
  - Check if `first, first + 1, ..., first + k - 1` are present with count >= `count(first)`.
  - Decrement their counts; remove keys whose count reaches 0.

TIME COMPLEXITY: O(N log N) using TreeMap.
SPACE COMPLEXITY: O(N) for frequency map.
*/

import java.util.TreeMap;

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (!map.isEmpty()) {
            int first = map.firstKey();
            int count = map.get(first);

            for (int i = 0; i < k; i++) {
                int next = first + i;
                if (!map.containsKey(next) || map.get(next) < count) {
                    return false;
                }
                if (map.get(next) == count) {
                    map.remove(next);
                } else {
                    map.put(next, map.get(next) - count);
                }
            }
        }
        return true;
    }
}
