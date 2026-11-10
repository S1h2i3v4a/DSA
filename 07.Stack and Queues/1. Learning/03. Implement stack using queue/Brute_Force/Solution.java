/*
QUESTION:
Implement a last-in-first-out (LIFO) stack using queues.
Implement the functions: push, pop, top, and empty.

APPROACH:
- Use two queues: `q1` and `q2`.
- When pushing `x`:
  1. Add `x` to `q2`.
  2. Move all elements from `q1` to `q2`.
  3. Swap the references of `q1` and `q2`.
- Now the newly added element is at the front of `q1`.
- `pop()` and `top()` simply access the front of `q1` in O(1).

TIME COMPLEXITY:
- push: O(N)
- pop, top, empty: O(1)
SPACE COMPLEXITY: O(N) using two queues.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public Solution() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.isEmpty() ? -1 : q1.poll();
    }

    public int top() {
        return q1.isEmpty() ? -1 : q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
