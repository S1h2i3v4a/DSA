/**
 * Problem Name: Binary Tree Level Order Traversal
 * Platform: LeetCode (LC 102)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the total number of nodes in the binary tree
 * Space Complexity: O(W) where W is the maximum width of the binary tree (Queue space)
 * 
 * Approach:
 * Breadth-First Search (BFS):
 * Use a Queue to process tree level by level.
 * 1. Push root to Queue.
 * 2. While Queue is not empty, get current level size `levelSize = queue.size()`.
 * 3. Loop `levelSize` times: poll node, add value to current level list, push non-null children.
 * 4. Append level list to final 2D result list.
 */

import java.util.*;

class Level_Order_Traversal {
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

    public static void main(String[] args) {
        Level_Order_Traversal solver = new Level_Order_Traversal();
        // Tree: 3 -> (9, 20 -> (15, 7))
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Level Order Traversal: " + solver.levelOrder(root));
        // Expected: [[3], [9, 20], [15, 7]]
    }
}
