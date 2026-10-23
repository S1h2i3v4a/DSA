/*
Question:
Write a program to implement a Stack using Array. Your task is to use the class as shown in the comments in the code editor and complete the functions push() and pop() to implement a stack.

Approach:
- We use a class `MyStack` to represent the stack.
- The stack is implemented using an array `arr` and a top variable to keep track of the top element.
- The class provides two member functions: `push(int)` to push an element onto the stack and `pop()` to pop an element from the stack.
- The `push(int)` function increments the top and adds the element to the array at the current top index.
- The `pop()` function checks if the stack is empty (top = -1), in which case it returns -1. Otherwise, it retrieves the element at the top index, decrements the top, and returns the element.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Implement_Stack_Using_Array {

    private int[] arr;
    private int top;
    private int capacity;

    public Implement_Stack_Using_Array(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int x) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            return -1; // Stack Underflow
        }
        return arr[top--];
    }

    public int peek() {
        if (top == -1) return -1;
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Implement_Stack_Using_Array stack = new Implement_Stack_Using_Array(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Is empty? " + stack.isEmpty());
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
