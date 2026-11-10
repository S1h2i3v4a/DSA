/*
QUESTION:
Return the maximum total number of fruits you can pick with 2 baskets.

APPROACH:
- Sliding window with a HashMap storing fruit type and its count in current window.
- Expand `right`: add `fruits[right]` to map.
- While `map.size() > 2`:
  - Decrement count of `fruits[left]`. If count reaches 0, remove it.
  - `left++`.
- Update `maxFruits = Math.max(maxFruits, right - left + 1)`.

TIME COMPLEXITY: O(2 * N) = O(N).
SPACE COMPLEXITY: O(1) map has at most 3 entries.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, maxFruits = 0;

        for (int right = 0; right < n; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }
}
