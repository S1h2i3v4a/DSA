/* QUESTION:

Given a binary tree, find the inorder traversal of the tree without using extra space.

APPROACH:

We can achieve a non-recursive inorder traversal without using extra space by modifying the binary tree itself.

1. We start with the current node as the root.
2. While the current node is not NULL, we do the following:
   - If the current node does not have a left child, we visit the current node and move to its right child.
   - If the current node has a left child, we find the rightmost node of its left subtree.
     - If the rightmost node does not have a right child, we make the right child point to the current node and move to the left child.
     - If the rightmost node already has a right child (which points back to the current node), we reset the right child to NULL, visit the current node, and move to its right child.
3. We repeat this process until we have visited all the nodes in the tree.
4. Finally, we return the result vector containing the inorder traversal.

COMPLEXITY ANALYSIS:

Let n be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n) since we visit each node once.
- Space Complexity: The space complexity is O(1) since we don't use any extra space.

CODE:
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
class Morris_Inorder_Traversal {
    public List<Integer> getInorder(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                inorder.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    // Create thread and move to left child
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    // Left subtree traversed, remove thread, visit root, move to right
                    prev.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return inorder;
    }

    public static void main(String[] args) {
        Morris_Inorder_Traversal solver = new Morris_Inorder_Traversal();
        // Tree: 1 -> (2 -> (4, 5), 3)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Morris Inorder Traversal: " + solver.getInorder(root));
        // Expected: [4, 2, 5, 1, 3]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
