/**
 * Problem Name: Binary Tree Zigzag Level Order Traversal
 * Platform: LeetCode (LC 103)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is total number of nodes
 * Space Complexity: O(W) where W is maximum width of binary tree
 * 
 * Approach:
 * BFS with Direction Flag:
 * Use Queue for level order traversal.
 * Maintain boolean flag leftToRight = true.
 * For each level:
 * - Create a LinkedList for current level elements.
 * - If leftToRight is true: add to end (add/addLast).
 * - If leftToRight is false: add to beginning (addFirst).
 * - Toggle leftToRight = !leftToRight after each level.
 */

import java.util.*;

class Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();

                if (leftToRight) {
                    currentLevel.addLast(curr.val);
                } else {
                    currentLevel.addFirst(curr.val);
                }

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            result.add(currentLevel);
            leftToRight = !leftToRight;
        }

        return result;
    }

    public static void main(String[] args) {
        Binary_Tree_Zigzag_Level_Order_Traversal solver = new Binary_Tree_Zigzag_Level_Order_Traversal();
        // Tree: 3 -> (9, 20 -> (15, 7))
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Zigzag Level Order: " + solver.zigzagLevelOrder(root));
        // Expected: [[3], [20, 9], [15, 7]]
    }
}
