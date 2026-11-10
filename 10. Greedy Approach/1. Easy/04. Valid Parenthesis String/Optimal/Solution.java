/*
QUESTION:
Determine if string `s` is valid using a greedy range approach in O(N) time and O(1) space.

APPROACH:
- Maintain the range of possible open parentheses count `[minOpen, maxOpen]`.
- For each character:
  - '(': `minOpen++`, `maxOpen++`.
  - ')': `minOpen = Math.max(0, minOpen - 1)`, `maxOpen--`.
  - '*': `minOpen = Math.max(0, minOpen - 1)` (if '*='')'), `maxOpen++` (if '*='(').
- If `maxOpen < 0`, more ')' than possible '(' and '*', return false.
- At end, string is valid if `minOpen == 0`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0, maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen = Math.max(0, minOpen - 1);
                maxOpen--;
            } else {
                minOpen = Math.max(0, minOpen - 1);
                maxOpen++;
            }
            if (maxOpen < 0) return false;
        }
        return minOpen == 0;
    }
}
