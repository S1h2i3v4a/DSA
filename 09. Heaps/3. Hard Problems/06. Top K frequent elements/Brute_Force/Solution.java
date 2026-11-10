/*
QUESTION:
Given an integer array `nums` and an integer `k`, return the `k` most frequent elements.

APPROACH:
- Count frequencies in a HashMap.
- Convert map entries to a list and sort by frequency descending.
- Extract the keys of the first `k` entries.

TIME COMPLEXITY: O(D log D) where D is number of unique elements.
SPACE COMPLEXITY: O(D) for HashMap and list.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }
        return result;
    }
}
