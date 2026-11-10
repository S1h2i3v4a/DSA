/*
QUESTION:
Implement a Stack using a singly linked list.
Support push, pop, peek, and isEmpty operations.

APPROACH:
- Maintain a pointer `top` referencing the head of the linked list.
- `push(x)`: Create a new node with value `x`, set `node.next = top`, and update `top = node`.
- `pop()`: If empty return -1, else store `top.val`, update `top = top.next`, and return stored value.
- `peek()`: Return `top.val`.
- `isEmpty()`: Return `top == null`.

TIME COMPLEXITY: O(1) for all operations (push, pop, peek, isEmpty).
SPACE COMPLEXITY: O(N) where N is the number of elements in the stack.
*/

class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Solution {
    private Node top;

    public Solution() {
        this.top = null;
    }

    public void push(int x) {
        Node newNode = new Node(x);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) return -1;
        int val = top.data;
        top = top.next;
        return val;
    }

    public int peek() {
        if (top == null) return -1;
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
