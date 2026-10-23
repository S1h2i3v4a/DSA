/*
Question:
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
The following rules define a valid string:
- Any left parenthesis '(' must have a corresponding right parenthesis ')'.
- Any right parenthesis ')' must have a corresponding left parenthesis '('.
- Left parenthesis '(' must go before the corresponding right parenthesis ')'.
- '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example:
Input: s = "(*))"
Output: true

Approach:
- We maintain two counters `cmin` and `cmax` to keep track of the minimum and maximum possible number of open parentheses.
- We iterate over the characters in the string.
- For each character:
  - If it is '(', we increment both `cmin` and `cmax`.
  - If it is ')', we decrement `cmax` and update `cmin` to the maximum of `cmin - 1` and 0 (to handle cases where '*' is treated as '(').
  - If it is '*', we increment `cmax` (considering '*' as '(') and update `cmin` to the maximum of `cmin - 1` and 0 (to handle cases where '*' is treated as ')').
  - If `cmax` becomes negative at any point, it means even after treating all '*' as '(' we can't maintain balance, so we return false.
- Finally, we check if `cmin` is 0, which means we have balanced parentheses.

Complexity Analysis:
- The time complexity of this approach is O(N), where N is the length of the string s.
- We iterate over each character once.
- The space complexity is O(1) as we are using only a constant amount of extra space.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
