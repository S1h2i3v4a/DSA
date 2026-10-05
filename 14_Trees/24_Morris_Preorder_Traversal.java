/**
 * Problem Name: Morris Preorder Traversal of a Binary Tree
 * Platform: LeetCode (LC 144) / GeeksforGeeks / Coder Army
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) amortized (each edge is traversed at most 3 times)
 * Space Complexity: O(1) auxiliary space (no recursion stack, no explicit stack)
 * 
 * Approach:
 * Threaded Binary Tree (Morris Traversal):
 * While curr != null:
 *   1. If curr.left == null:
 *        visit curr.val (add to preorder list)
 *        curr = curr.right
 *   2. Else:
 *        Find the rightmost node in curr's left subtree (inorder predecessor: prev).
 *        Case A: If prev.right == null:
 *          Establish temporary thread: prev.right = curr
 *          Visit curr.val (in preorder, root is visited BEFORE traversing left subtree)
 *          curr = curr.left
 *        Case B: If prev.right == curr:
 *          Remove thread: prev.right = null
 *          curr = curr.right
 */

import java.util.*;

class Morris_Preorder_Traversal {
    public List<Integer> getPreorder(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                preorder.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    // Create thread and visit node before traversing left
                    prev.right = curr;
                    preorder.add(curr.val);
                    curr = curr.left;
                } else {
                    // Remove thread and move to right subtree
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }

        return preorder;
    }

    public static void main(String[] args) {
        Morris_Preorder_Traversal solver = new Morris_Preorder_Traversal();
        // Tree: 1 -> (2 -> (4, 5), 3)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Morris Preorder Traversal: " + solver.getPreorder(root));
        // Expected: [1, 2, 4, 5, 3]
    }
}
