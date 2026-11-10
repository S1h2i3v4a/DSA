/*
QUESTION:
Given an array `A` of integers of length `n`, find the nearest smaller number for every element on the left side.
If no smaller element is present, assign -1.

APPROACH:
- Use a monotonic increasing stack from left to right.
- For each element `A.get(i)`:
  - Pop elements from stack while `stack.peek() >= A.get(i)`.
  - If stack empty, previous smaller is -1; else `stack.peek()`.
  - Push `A.get(i)` onto stack.

TIME COMPLEXITY: O(N) since each element is pushed/popped at most once.
SPACE COMPLEXITY: O(N) for the stack.
*/

import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int val : A) {
            while (!stack.isEmpty() && stack.peek() >= val) {
                stack.pop();
            }
            result.add(stack.isEmpty() ? -1 : stack.peek());
            stack.push(val);
        }
        return result;
    }
}
