/*
QUESTION:
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
(i.e., from left to right, then right to left for the next level and alternate between).

APPROACH:
- BFS level order traversal with a boolean flag `leftToRight`.
- For each level:
  - Create list of size `levelSize`.
  - If `leftToRight` is true, put at index `i`.
  - If `leftToRight` is false, put at index `levelSize - 1 - i`.
  - Flip flag after each level.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(N) for queue.
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>(size);
            for (int i = 0; i < size; i++) currentLevel.add(0);

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int index = leftToRight ? i : (size - 1 - i);
                currentLevel.set(index, node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            leftToRight = !leftToRight;
            result.add(currentLevel);
        }
        return result;
    }
}
