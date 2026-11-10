/*
QUESTION:
Given a parentheses string s, return minimum number of insertions required to make s valid.

APPROACH:
- Use a Stack to store unmatched parentheses.
- For each character:
  - If '(', push onto stack.
  - If ')', if stack has '(', pop it; else push ')'.
- Stack size at the end gives the number of insertions needed.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(N) auxiliary space for stack.
*/

import java.util.Stack;

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.size();
    }
}
