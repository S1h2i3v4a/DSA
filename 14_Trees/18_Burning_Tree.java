/**
 * Problem Name: Burning Tree
 * Platform: GeeksforGeeks
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) where N is the total number of nodes in tree
 * Space Complexity: O(N) for parent map, visited map, and BFS queue
 * 
 * Approach:
 * 1. Build parent pointers mapping using BFS and identify the target node pointer.
 * 2. Radial BFS Fire Spread:
 *    - Start BFS from target node.
 *    - Each second, fire spreads to all unburnt neighbors (left child, right child, and parent).
 *    - Track visited/burnt nodes to avoid burning twice.
 * 3. Count total elapsed seconds until all reachable nodes are burnt.
 */

import java.util.*;

class Burning_Tree {
    public int minTime(TreeNode root, int target) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode targetNode = buildParentMapAndFindTarget(root, parentMap, target);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(targetNode);
        visited.add(targetNode);
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean spread = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                // Left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    queue.offer(curr.left);
                    spread = true;
                }
                // Right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    queue.offer(curr.right);
                    spread = true;
                }
                // Parent
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                    spread = true;
                }
            }

            if (spread) {
                time++;
            }
        }

        return time;
    }

    private TreeNode buildParentMapAndFindTarget(TreeNode root, Map<TreeNode, TreeNode> parentMap, int target) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode targetNode = null;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.val == target) {
                targetNode = curr;
            }

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }

        return targetNode;
    }

    public static void main(String[] args) {
        Burning_Tree solver = new Burning_Tree();
        // Tree: 1 -> (2 -> (4, 5 -> (null, 7)), 3 -> (null, 6 -> (null, 8)))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(8);

        System.out.println("Time to burn complete tree starting from 8: " + solver.minTime(root, 8)); // Expected: 7
    }
}
