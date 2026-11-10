/*
QUESTION:
Return the maximum total number of fruits you can pick with 2 baskets in optimal O(N) single pass.

APPROACH:
- Non-shrinking sliding window.
- Expand `right`: add `fruits[right]` to map.
- If `map.size() > 2`:
  - Shrink window by 1: decrement `fruits[left]`, remove if 0, `left++`.
- Window size never decreases, preserving the maximum valid window seen.
- Return `fruits.length - left`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) map has at most 3 entries.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0;

        for (right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            if (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }
        }
        return right - left;
    }
}
