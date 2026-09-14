/*
 * Problem Name: Generate All Binary Strings Without Consecutive 1s
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(2^N)
 * Space Complexity: O(N)
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        generate(n, "", result);
        return result;
    }

    private static void generate(int n, String current, List<String> result) {
        if (current.length() == n) {
            result.add(current);
            return;
        }

        generate(n, current + "0", result);
        if (current.isEmpty() || current.charAt(current.length() - 1) != '1') {
            generate(n, current + "1", result);
        }
    }
}
