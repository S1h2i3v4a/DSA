/**
 * Problem Name: Morris Inorder Traversal of a Binary Tree
 * Platform: LeetCode (LC 94) / GeeksforGeeks / Coder Army
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) amortized (each edge traversed at most 3 times)
 * Space Complexity: O(1) auxiliary space (no recursion, no stack)
 * 
 * Approach:
 * Threaded Binary Tree (Morris Traversal):
 * While curr != null:
 *   1. If curr.left == null:
 *        visit curr.val (add to inorder list)
 *        curr = curr.right
 *   2. Else:
 *        Find the rightmost node in curr's left subtree (inorder predecessor: prev).
 *        Case A: If prev.right == null:
 *          Establish temporary thread: prev.right = curr
 *          curr = curr.left
 *        Case B: If prev.right == curr:
 *          Remove thread: prev.right = null
 *          Visit curr.val (in inorder, root is visited AFTER traversing left subtree)
 *          curr = curr.right
 */

import java.util.*;

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
