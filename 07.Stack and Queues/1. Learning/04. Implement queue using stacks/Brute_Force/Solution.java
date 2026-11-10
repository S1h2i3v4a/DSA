/*
QUESTION:
Implement a first-in-first-out (FIFO) queue using two stacks.
Implement push, pop, peek, and empty.

APPROACH:
- Use two stacks `s1` and `s2`.
- On `push(x)`:
  1. Move all elements from `s1` to `s2`.
  2. Push `x` into `s1`.
  3. Move all elements back from `s2` to `s1`.
- This ensures the oldest element is always at the top of `s1`.
- `pop()` and `peek()` are O(1).

TIME COMPLEXITY:
- push: O(N)
- pop, peek, empty: O(1)
SPACE COMPLEXITY: O(N) across two stacks.
*/

import java.util.Stack;

class Solution {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public Solution() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s1.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    public int pop() {
        return s1.isEmpty() ? -1 : s1.pop();
    }

    public int peek() {
        return s1.isEmpty() ? -1 : s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty();
    }
}
