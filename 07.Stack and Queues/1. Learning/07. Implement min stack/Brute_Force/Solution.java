/*
QUESTION:
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement MinStack with push, pop, top, getMin.

APPROACH:
- Use a stack that stores pairs of `(value, minAtThisState)`.
- When pushing `val`, new min is `Math.min(val, currentMin)`.
- `getMin()` simply returns the `min` stored in the top pair.

TIME COMPLEXITY: O(1) for push, pop, top, getMin.
SPACE COMPLEXITY: O(2 * N) = O(N) since each entry stores two integers.
*/

import java.util.Stack;

class Pair {
    int val;
    int min;
    Pair(int val, int min) {
        this.val = val;
        this.min = min;
    }
}

class Solution {
    private Stack<Pair> stack;

    public Solution() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Pair(val, val));
        } else {
            stack.push(new Pair(val, Math.min(val, stack.peek().min)));
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peek().val;
    }

    public int getMin() {
        return stack.isEmpty() ? -1 : stack.peek().min;
    }
}
