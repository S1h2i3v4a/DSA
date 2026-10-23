/*
QUESTION:
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class.
MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

Example:
Input:
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
Output:
[null,null,null,null,-3,null,0,-2]
Explanation:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

APPROACH:
- We can use an auxiliary stack to keep track of the difference of an element from the minimum element at each step.
- When pushing an element, we push the difference of an element from the minimum element at that time.
- When popping an element, we check stack top if it's -ve it means that this element is the minimum so we update the mini as we popping the current minimum.
- To get the top element, we add the current minimum to the top element of the main stack.
- To get the minimum element, we simply return the current minimum.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
