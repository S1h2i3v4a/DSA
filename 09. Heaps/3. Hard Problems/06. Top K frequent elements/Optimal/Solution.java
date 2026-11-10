/*
QUESTION:
Return the `k` most frequent elements in O(N) using Bucket Sort.

APPROACH:
- Count frequencies using a HashMap.
- Create an array of lists `buckets` of size `nums.length + 1`, where index `f` stores all elements with frequency `f`.
- Iterate from highest frequency `nums.length` down to 1, collecting elements into result until `k` elements are gathered.

TIME COMPLEXITY: O(N) linear time.
SPACE COMPLEXITY: O(N) for frequency map and buckets.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        int[] result = new int[k];
        int count = 0;
        for (int f = buckets.length - 1; f >= 0 && count < k; f--) {
            if (buckets[f] != null) {
                for (int num : buckets[f]) {
                    result[count++] = num;
                    if (count == k) break;
                }
            }
        }
        return result;
    }
}
