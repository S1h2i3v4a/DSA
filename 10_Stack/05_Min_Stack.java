/**
 * Problem Name: Min Stack
 * Platform: LeetCode (155)
 * Difficulty: Medium
 * 
 * Time Complexity: O(1) for push, pop, top, and getMin.
 * Space Complexity: O(N) for stack elements storage.
 * 
 * Approach:
 * Value-Min Pair Stack.
 * Each node in stack stores its own value alongside the minimum value seen up to that point.
 * `push(val)` calculates `min(val, currentMin)` and pushes a Pair(val, min).
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Min_Stack {

    private static class Element {
        int val;
        int min;

        Element(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private Deque<Element> stack;

    public Min_Stack() {
        stack = new ArrayDeque<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Element(val, val));
        } else {
            int currentMin = Math.min(val, stack.peek().min);
            stack.push(new Element(val, currentMin));
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }

    public static void main(String[] args) {
        Min_Stack minStack = new Min_Stack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Get min: " + minStack.getMin()); // -3
        minStack.pop();
        System.out.println("Top element: " + minStack.top()); // 0
        System.out.println("Get min: " + minStack.getMin()); // -2
    }
}
