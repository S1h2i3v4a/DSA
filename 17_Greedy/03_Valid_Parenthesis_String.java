/**
 * Problem Name: Valid Parenthesis String
 * Platform: LeetCode (LC 678)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass through the string
 * Space Complexity: O(1) space complexity using min/max open counters
 * 
 * Approach:
 * Maintain range [minOpen, maxOpen] representing possible number of open parentheses.
 * - '(': minOpen++, maxOpen++
 * - ')': minOpen--, maxOpen--
 * - '*': minOpen-- (if treated as ')'), maxOpen++ (if treated as '(')
 * 
 * If maxOpen < 0 at any point, string is invalid (too many closing parentheses).
 * minOpen cannot be negative (reset to 0).
 * String is valid if minOpen == 0 at the end.
 */

class Valid_Parenthesis_String {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--;
                maxOpen++;
            }

            if (maxOpen < 0) return false;
            if (minOpen < 0) minOpen = 0;
        }

        return minOpen == 0;
    }

    public static void main(String[] args) {
        Valid_Parenthesis_String solver = new Valid_Parenthesis_String();
        System.out.println("Is '(*)' valid: " + solver.checkValidString("(*)"));   // true
        System.out.println("Is '(*))' valid: " + solver.checkValidString("(*))")); // true
        System.out.println("Is ')(' valid: " + solver.checkValidString(")("));     // false
    }
}
