/*
Question:
You have been given a Binary Tree of 'N' nodes, where the nodes have integer values.
Your task is to find the In-Order, Pre-Order, and Post-Order traversals of the given binary tree.

Approach:
- We can perform the tree traversals recursively using three functions:
    - In-Order Traversal: Visit the left subtree, visit the current node, visit the right subtree.
    - Pre-Order Traversal: Visit the current node, visit the left subtree, visit the right subtree.
    - Post-Order Traversal: Visit the left subtree, visit the right subtree, visit the current node.
- For each traversal, we can maintain a vector to store the values of the visited nodes in the respective order.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(N) as we store the values of the nodes in vectors for each traversal.

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
class Preorder_Inorder_Postorder_In_One_Traversal {
    private static class Pair {
        TreeNode node;
        int state;

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void allTraversals(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        if (root == null) return;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair p = stack.peek();

            if (p.state == 1) {
                preorder.add(p.node.val);
                p.state++;
                if (p.node.left != null) {
                    stack.push(new Pair(p.node.left, 1));
                }
            } else if (p.state == 2) {
                inorder.add(p.node.val);
                p.state++;
                if (p.node.right != null) {
                    stack.push(new Pair(p.node.right, 1));
                }
            } else {
                postorder.add(p.node.val);
                stack.pop();
            }
        }

        System.out.println("Preorder:  " + preorder);
        System.out.println("Inorder:   " + inorder);
        System.out.println("Postorder: " + postorder);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        allTraversals(root);
        // Preorder:  [1, 2, 4, 5, 3]
        // Inorder:   [4, 2, 5, 1, 3]
        // Postorder: [4, 5, 2, 3, 1]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
