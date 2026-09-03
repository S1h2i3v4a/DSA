/*
 * Problem Name: Check if Two Arrays Are Equal or Not
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.HashMap;

class Solution {
    public static boolean checkEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        HashMap<Integer, Integer> freqA = new HashMap<>();
        HashMap<Integer, Integer> freqB = new HashMap<>();
        for (int num : a) freqA.put(num, freqA.getOrDefault(num, 0) + 1);
        for (int num : b) freqB.put(num, freqB.getOrDefault(num, 0) + 1);
        return (freqA.equals(freqB));
    }
}
