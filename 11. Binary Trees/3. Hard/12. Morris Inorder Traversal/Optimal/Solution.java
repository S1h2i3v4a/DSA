/*
QUESTION:
Perform Inorder Traversal in O(1) auxiliary space using Morris Traversal.

APPROACH:
- Use threaded binary tree:
  - If `curr.left == null`: visit `curr.val`, move `curr = curr.right`.
  - Else: find inorder predecessor.
    - If `prev.right == null`: create thread `prev.right = curr`, move `curr = curr.left`.
    - If `prev.right == curr`: break thread `prev.right = null`, visit `curr.val` (inorder visit!), move `curr = curr.right`.

TIME COMPLEXITY: O(N).
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
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    prev.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return inorder;
    }
}
