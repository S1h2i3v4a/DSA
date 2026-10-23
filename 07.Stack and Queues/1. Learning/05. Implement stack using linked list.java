/*
Question:
You have a linked list and you have to implement the functionalities push and pop of stack using this given linked list. Your task is to use the class as shown in the comments in the code editor and complete the functions push() and pop() to implement a stack.

Approach:
- We use the class `MyStack` to represent the stack implemented using a linked list.
- The stack is implemented using a singly linked list where each node represents an element in the stack.
- The `push(int)` function inserts an element at the top of the stack by creating a new node and updating the `next` pointer to point to the current top.
- The `pop()` function removes the top element from the stack by updating the `top` pointer to the next node and returning the data of the removed node.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
