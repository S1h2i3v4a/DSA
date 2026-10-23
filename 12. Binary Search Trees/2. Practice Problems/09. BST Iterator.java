/*
Question:
Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.

boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.

int next() Moves the pointer to the right, then returns the number at the pointer.

Approach:
- We are using a stack to keep track of the nodes during the in-order traversal of the BST.
- In the constructor, we initialize the stack by pushing all the leftmost nodes in the BST to the stack.
- The next() function returns the top element of the stack (which will be the next smallest element in the BST) and pops it from the stack.
- If the node has a right child, we push all the leftmost nodes in the right subtree to the stack before returning the node's value.
- The hasNext() function simply checks if the stack is empty or not.

Time Complexity:
- The constructor takes O(h) time, where h is the height of the BST, as it traverses the leftmost path in the BST.
- The next() and hasNext() functions take O(1) time as they only perform stack operations.

Space Complexity:
- The space complexity is O(h), where h is the height of the BST, as the stack stores the nodes in the leftmost path of the BST.

*/

import java.io.*;
import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// CODE:-
class BST_Iterator {
    private Stack<TreeNode> stack;

    public BST_Iterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        pushAllLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        // BST: 7 -> (3, 15 -> (9, 20))
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BST_Iterator iterator = new BST_Iterator(root);
        System.out.println("next(): " + iterator.next());       // Expected: 3
        System.out.println("next(): " + iterator.next());       // Expected: 7
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: true
        System.out.println("next(): " + iterator.next());       // Expected: 9
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: true
        System.out.println("next(): " + iterator.next());       // Expected: 15
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: true
        System.out.println("next(): " + iterator.next());       // Expected: 20
        System.out.println("hasNext(): " + iterator.hasNext()); // Expected: false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
