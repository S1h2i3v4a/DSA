/*
Question:
Given the root of a binary tree, return the postorder traversal of its nodes' values.

Approach:
- postorder traversal visits the left subtree first, followed by the right subtree, and then the root node.
- We can solve this problem recursively by following the postorder traversal order.

Complexity Analysis:
- Since we are visiting each node once, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(N) as we are using the call stack for recursion.

Code:
*/

import java.io.*;
import java.util.*;

// CODE:-
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

class Binary_Tree_Traversals {

    // Preorder Traversal Iterative
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val);

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }

        return result;
    }

    // Inorder Traversal Iterative
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }

    public static void main(String[] args) {
        Binary_Tree_Traversals solver = new Binary_Tree_Traversals();
        // Construct Tree: 1 -> (null, 2 -> 3)
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println("Preorder Traversal: " + solver.preorderTraversal(root)); // [1, 2, 3]
        System.out.println("Inorder Traversal: " + solver.inorderTraversal(root));   // [1, 3, 2]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
