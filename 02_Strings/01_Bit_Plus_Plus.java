/*
 * Problem Name: Bit++
 * Platform: Codeforces (282A)
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), ans = 0;
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            if (s.charAt(0) == '+' || s.charAt(2) == '+') ans++;
            else ans--;
        }
        System.out.print(ans);
    }
}
