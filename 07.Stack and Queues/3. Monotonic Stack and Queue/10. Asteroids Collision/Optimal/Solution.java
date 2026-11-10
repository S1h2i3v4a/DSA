/*
QUESTION:
We are given an array `asteroids` of integers representing asteroids in a row.
Find out the state of the asteroids after all collisions.

APPROACH:
- Use a monotonic stack to simulate collisions in a single pass.
- For each asteroid:
  - If asteroid > 0 (moving right), push onto stack.
  - If asteroid < 0 (moving left):
    - Collide with positive asteroids on top of stack while `stack.peek() > 0` and `stack.peek() < |asteroid|`. Pop smaller ones.
    - If top equals `|asteroid|`, both explode: pop top and do not push current.
    - If stack is empty or top is negative, push current asteroid.

TIME COMPLEXITY: O(N) single pass; each asteroid pushed and popped at most once.
SPACE COMPLEXITY: O(N) for stack storage.
*/

import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == -a) {
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                }
            }
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
