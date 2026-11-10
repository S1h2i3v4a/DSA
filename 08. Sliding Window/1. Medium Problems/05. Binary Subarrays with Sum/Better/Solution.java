/*
QUESTION:
Given a binary array `nums` and an integer `goal`, return the number of non-empty subarrays with sum `goal`.

APPROACH:
- Use prefix sum with a HashMap (or array).
- For each element, maintain `currSum += nums[i]`.
- If `map.containsKey(currSum - goal)`, add `map.get(currSum - goal)` to count.
- Add `currSum` to map.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(N) for prefix sum frequency map.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int currSum = 0, count = 0;

        for (int num : nums) {
            currSum += num;
            if (map.containsKey(currSum - goal)) {
                count += map.get(currSum - goal);
            }
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return count;
    }
}
