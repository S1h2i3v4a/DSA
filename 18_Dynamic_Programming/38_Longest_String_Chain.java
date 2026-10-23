/**
 * Problem Name: Longest String Chain
 * Platform: LeetCode (LC 1048) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * log N + N * L^2) where N is number of words, L is max word length (<= 16)
 * Space Complexity: O(N) for HashMap storing DP states
 *
 * Approach:
 *   - A word A is a predecessor of word B if inserting exactly one letter into A creates B.
 *   - Sort words by string length in ascending order.
 *   - Maintain a HashMap<String, Integer> dp mapping each word to the length of the longest chain ending at it.
 *   - For each word:
 *     Iterate over all L possible deletions of 1 character to form predecessor candidate `prev`.
 *     If `prev` exists in our dp map:
 *       dp.put(word, max(dp.get(word), 1 + dp.get(prev))).
 *   - Track the maximum chain length across all words.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LongestStringChain {

    public static int longestStrChain(String[] words) {
        // Sort words by length ascending
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        Map<String, Integer> dp = new HashMap<>();
        int maxChain = 1;

        for (String word : words) {
            int currentBest = 1;
            // Generate all possible predecessor strings by omitting one character
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                if (dp.containsKey(prev)) {
                    currentBest = Math.max(currentBest, 1 + dp.get(prev));
                }
            }
            dp.put(word, currentBest);
            maxChain = Math.max(maxChain, currentBest);
        }

        return maxChain;
    }

    public static void main(String[] args) {
        // Test Case 1: words = ["a","b","ba","bca","bda","bdca"] -> Expected: 4 ("a"->"ba"->"bda"->"bdca")
        String[] w1 = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println("Test 1: " + longestStrChain(w1)); // 4

        // Test Case 2: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"] -> Expected: 5
        String[] w2 = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println("Test 2: " + longestStrChain(w2)); // 5
    }
}
