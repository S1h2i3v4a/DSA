/*
QUESTION:
Implement a Stack using an Array. The Stack should support push, pop, peek, and isEmpty operations.

APPROACH:
- Use a fixed-size array `arr` and a pointer `top` initialized to -1.
- `push(x)`: Increment `top` and store `arr[top] = x`.
- `pop()`: If empty return -1, else return `arr[top--]`.
- `peek()`: If empty return -1, else return `arr[top]`.
- `isEmpty()`: Check if `top == -1`.

TIME COMPLEXITY: O(1) for all operations (push, pop, peek, isEmpty).
SPACE COMPLEXITY: O(N) where N is the maximum capacity of the stack.
*/

class Solution {
    int[] arr;
    int top;
    int capacity;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.top = -1;
    }

    public Solution() {
        this(1000);
    }

    public void push(int x) {
        if (top == capacity - 1) return;
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) return -1;
        return arr[top--];
    }

    public int peek() {
        if (top == -1) return -1;
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
