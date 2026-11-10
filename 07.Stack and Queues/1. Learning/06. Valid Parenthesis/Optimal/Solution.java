/*
QUESTION:
Given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

APPROACH:
- Use a stack to track opening brackets.
- Traverse through the characters:
  - If open bracket '(', '{', '[', push onto stack.
  - If closing bracket, check if stack is non-empty and top matches the closing bracket. If not, return false.
- After traversal, the string is valid if and only if the stack is empty.

TIME COMPLEXITY: O(N) where N is the length of the string.
SPACE COMPLEXITY: O(N) for the stack in the worst case.
*/

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }
}
