/*
 * Problem Name: Chewbaсca and Number
 * Platform: Codeforces (514A)
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            if (i == 0 && digit == 9) ans.append(9);
            else ans.append(Math.min(digit, 9 - digit));
        }
        System.out.print(ans.toString());
    }
}
