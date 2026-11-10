/*
QUESTION:
Given the root of a binary tree, return the Right Side View of the tree.
(The values of nodes you can see ordered from top to bottom when looking from the right).

APPROACH:
- Modified recursive preorder traversal: visit `Root -> Right -> Left`.
- Track `level` (starting from 0).
- If `level == result.size()`, this is the first node encountered at this level from the right side, so add `node.val`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private void rightView(TreeNode node, int level, List<Integer> res) {
        if (node == null) return;
        if (level == res.size()) {
            res.add(node.val);
        }
        rightView(node.right, level + 1, res);
        rightView(node.left, level + 1, res);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, 0, result);
        return result;
    }
}
