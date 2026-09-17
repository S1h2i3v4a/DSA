/**
 * Problem Name: Palindrome Partitioning
 * Platform: LeetCode (131)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N * 2^N) where N is the length of the string s.
 * Space Complexity: O(N) for recursion depth stack.
 * 
 * Approach:
 * Backtracking algorithm that partitions the string s into all possible palindrome substrings.
 * At each index `start`, we iterate through `end` from `start` to `s.length() - 1`.
 * If s.substring(start, end + 1) is a palindrome, we add it to current list and recurse for index `end + 1`.
 */

import java.util.ArrayList;
import java.util.List;

class Palindrome_Partitioning {

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, String s, List<String> currentPath, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentPath.add(s.substring(start, end + 1));
                backtrack(end + 1, s, currentPath, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> partitions = partition(s);
        System.out.println("Palindrome Partitions for \"" + s + "\": " + partitions);
    }
}
