/*
 * Problem Name: Counting Sort
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    public static String countSort(String s) {
        int[] arr = new int[26];
        for (char ch : s.toCharArray()) arr[ch - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0) { sb.append((char)(i + 'a')); arr[i]--; }
        }
        return sb.toString();
    }
}
