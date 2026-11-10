/*
QUESTION:
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time,
using O(1) auxiliary space beyond a single stack.

APPROACH:
- Use a single stack storing `Long` values and a variable `min`.
- When pushing `val`:
  - If stack empty: `min = val`, push `val`.
  - If `val >= min`: push `val`.
  - If `val < min`: push encoded value `2 * val - min`, update `min = val`.
- When popping:
  - If `top < min`: original value was `min`, restore previous min using `min = 2 * min - top`.
- `top()`:
  - If `top < min`: return `min`.
  - Else return `top`.

TIME COMPLEXITY: O(1) for push, pop, top, getMin.
SPACE COMPLEXITY: O(N) for storing elements in a single stack without extra pairs.
*/

import java.util.Stack;

class Solution {
    private Stack<Long> stack;
    private long min;

    public Solution() {
        stack = new Stack<>();
    }

    public void push(int val) {
        long v = val;
        if (stack.isEmpty()) {
            min = v;
            stack.push(v);
        } else if (v >= min) {
            stack.push(v);
        } else {
            stack.push(2 * v - min);
            min = v;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        long top = stack.pop();
        if (top < min) {
            min = 2 * min - top;
        }
    }

    public int top() {
        if (stack.isEmpty()) return -1;
        long top = stack.peek();
        if (top < min) {
            return (int) min;
        }
        return (int) top;
    }

    public int getMin() {
        return (int) min;
    }
}
