/**
 * Problem Name: Longest Substring with K Uniques
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) max 256 keys in HashMap.
 * 
 * Approach:
 * Sliding Window with HashMap Frequency Count.
 * Maintain `left` pointer and HashMap storing character frequencies in current window `[left, right]`.
 * 1. Expand `right` pointer and put character into map.
 * 2. While `map.size() > k`, shrink `left` by decrementing frequency and removing key when frequency == 0.
 * 3. If `map.size() == k`, update `maxLength = max(maxLength, right - left + 1)`.
 * Return `maxLength` if valid window was found, else -1.
 */

import java.util.HashMap;
import java.util.Map;

class Longest_Substring_With_K_Uniques {

    public static int longestkSubstr(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = -1;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            if (map.size() == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        System.out.println("Longest Substring with " + k + " Uniques: " + longestkSubstr(s, k));
    }
}
