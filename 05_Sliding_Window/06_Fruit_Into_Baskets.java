/**
 * Problem Name: Fruit Into Baskets
 * Platform: LeetCode (904)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) max 3 keys in HashMap.
 * 
 * Approach:
 * Sliding Window with Frequency HashMap.
 * Window must contain at most 2 distinct fruit types (basket capacity).
 * Expand `right` pointer and put fruit into map.
 * While `map.size() > 2`, shrink window from `left` by decrementing count and removing fruit key when count becomes 0.
 */

import java.util.HashMap;
import java.util.Map;

class Fruit_Into_Baskets {

    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
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

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};
        System.out.println("Maximum Fruits Collected: " + totalFruit(fruits));
    }
}
