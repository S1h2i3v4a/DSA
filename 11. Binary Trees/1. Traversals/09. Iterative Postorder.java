/*
Question:
Given the root of a binary tree, return the postorder traversal of its nodes' values.

Approach:
- We can perform a postorder traversal iteratively using a stack and a map.
- The idea is to push all the left children of a node into the stack until we reach a node with no left child.
- Then, we check if the right child of the node exists or has already been visited (using the map).
    - If it does not exist or has been visited, we add the node's value to the result vector and pop the node from the stack.
    - Otherwise, we push the right child into the stack and mark it as visited in the map.
- We repeat this process until the stack is empty and all nodes are traversed.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is also O(N) as we store the node values in the result vector and use a stack and a map to keep track of the nodes.

Code:
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
class Postorder_Traversal_Iterative {

    // 2-Stack Approach
    public List<Integer> postorderTwoStacks(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode curr = s1.pop();
            s2.push(curr);

            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }

        while (!s2.isEmpty()) {
            result.add(s2.pop().val);
        }

        return result;
    }

    // 1-Stack Approach
    public List<Integer> postorderOneStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Postorder_Traversal_Iterative solver = new Postorder_Traversal_Iterative();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println("Postorder (2 Stacks): " + solver.postorderTwoStacks(root)); // [3, 2, 1]
        System.out.println("Postorder (1 Stack):  " + solver.postorderOneStack(root));  // [3, 2, 1]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
