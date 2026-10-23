/*
Question:
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Approach:
- We can perform a level order traversal using a queue.
- We start by pushing the root node into the queue.
- Then, while the queue is not empty, we process each level by taking the size of the queue and iterating over the nodes at that level.
- For each node, we add its value to the current level's vector and push its left and right child nodes into the queue if they exist.
- After processing each level, we add the level's vector to the result vector.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(N) as we store the node values in the result vector.

Code:
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
