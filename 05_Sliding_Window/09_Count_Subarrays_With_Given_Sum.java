/**
 * Problem Name: Subarrays with Sum K / Count Subarrays with Given Sum
 * Platform: LeetCode (560) / GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(N) for Prefix Sum HashMap.
 * 
 * Approach:
 * Prefix Sum + HashMap Frequency Counting.
 * Maintain cumulative sum `currSum` and HashMap storing frequency of prefix sums.
 * At each index:
 * 1. `currSum += num`.
 * 2. If `map` contains `(currSum - k)`, add its frequency to `count`.
 * 3. Store/increment `currSum` frequency in map.
 */

import java.util.HashMap;
import java.util.Map;

class Count_Subarrays_With_Given_Sum {

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // Base case for prefix sum starting from index 0

        int currSum = 0;
        int count = 0;

        for (int num : nums) {
            currSum += num;

            if (prefixMap.containsKey(currSum - k)) {
                count += prefixMap.get(currSum - k);
            }

            prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println("Count of Subarrays with Sum " + k + ": " + subarraySum(nums, k));
    }
}
