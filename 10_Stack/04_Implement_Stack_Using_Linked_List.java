/**
 * Problem Name: Stack using Linked List
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(1) for push, pop, peek, isEmpty operations.
 * Space Complexity: O(N) for node allocation.
 * 
 * Approach:
 * Singly Linked List LIFO.
 * Pointer `top` references the head node of the list.
 * - push(x): Insert new node at head (`node.next = top; top = node;`).
 * - pop(): Fetch `top.data` and advance `top = top.next`. Return -1 if top == null.
 */

class Implement_Stack_Using_Linked_List {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;

    public Implement_Stack_Using_Linked_List() {
        top = null;
    }

    public void push(int x) {
        Node newNode = new Node(x);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) {
            return -1; // Underflow
        }
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

    public static void main(String[] args) {
        Implement_Stack_Using_Linked_List stack = new Implement_Stack_Using_Linked_List();
        stack.push(100);
        stack.push(200);
        System.out.println("Top element: " + stack.peek()); // 200
        System.out.println("Popped element: " + stack.pop()); // 200
        System.out.println("Is empty? " + stack.isEmpty()); // false
    }
}
