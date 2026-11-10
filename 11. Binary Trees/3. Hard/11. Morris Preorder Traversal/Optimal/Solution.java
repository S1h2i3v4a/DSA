/*
QUESTION:
Perform Preorder Traversal in O(1) auxiliary space using Morris Traversal.

APPROACH:
- Use threaded binary tree:
  - If `curr.left == null`: visit `curr.val`, move `curr = curr.right`.
  - Else: find inorder predecessor (rightmost node in left subtree).
    - If `prev.right == null`: create thread `prev.right = curr`, visit `curr.val` (preorder visit!), move `curr = curr.left`.
    - If `prev.right == curr`: break thread `prev.right = null`, move `curr = curr.right`.

TIME COMPLEXITY: O(N) each edge traversed at most twice.
SPACE COMPLEXITY: O(1) auxiliary space.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
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
                    prev.right = curr;
                    preorder.add(curr.val);
                    curr = curr.left;
                } else {
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return preorder;
    }
}
