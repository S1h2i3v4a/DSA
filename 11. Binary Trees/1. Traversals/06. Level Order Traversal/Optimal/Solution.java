/*
QUESTION:
Given the root of a binary tree, return the level order traversal of its nodes' values (i.e., from left to right, level by level).

APPROACH:
- Use a Queue for Breadth-First Search (BFS).
- Add root to queue.
- While queue is not empty:
  - Determine `levelSize = queue.size()`.
  - Poll `levelSize` nodes, add their values to current level list, and add their children to queue.
  - Add level list to result.

TIME COMPLEXITY: O(N) visiting every node once.
SPACE COMPLEXITY: O(N) for queue holding at most N/2 nodes at leaf level.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                currentLevel.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            result.add(currentLevel);
        }
        return result;
    }
}
