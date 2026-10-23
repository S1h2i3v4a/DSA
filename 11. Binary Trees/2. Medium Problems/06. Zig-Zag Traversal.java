/*
Question:
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Approach:
- We can use a queue to perform a level order traversal of the binary tree.
- To achieve the zigzag order, we can use a flag variable to keep track of the current direction (left to right or right to left) for each level.
- Initialize a boolean variable `ltor` to true, where true represents the left to right direction.
- While traversing each level, we store the values of the nodes in a temporary vector.
- If `ltor` is true, we push the left child first and then the right child into the queue for the next level.
- If `ltor` is false, we push the right child first and then the left child into the queue for the next level.
- After processing all the nodes in the current level, if `ltor` is false, we reverse the temporary vector to achieve the right to left direction for the next level.
- We alternate the value of `ltor` after processing each level to switch between left to right and right to left directions.
- Finally, we return the vector containing the zigzag level order traversal.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(M), where M is the maximum number of nodes at any level in the binary tree. In the worst case, the maximum number of nodes at any level can be N/2 (in a complete binary tree), resulting in O(N) space complexity.

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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
