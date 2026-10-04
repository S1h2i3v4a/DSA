/**
 * Problem Name: All Nodes Distance K in Binary Tree
 * Platform: LeetCode (LC 863)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) for parent mapping and BFS outward traversal
 * Space Complexity: O(N) for parent map, visited set, and queue
 * 
 * Approach:
 * 1. Build parent pointers mapping using BFS: Map<TreeNode, TreeNode> parentMap.
 * 2. Perform radial BFS from target node outward:
 *    - Neighbors are: left child, right child, and parent.
 *    - Keep track of visited nodes using a Set to avoid cycles.
 * 3. Increment distance level by level.
 * 4. When distance == k, the queue contains all nodes at distance k from target.
 */

import java.util.*;

class All_Nodes_Distance_K_In_Binary_Tree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);
        int currentDistance = 0;

        while (!queue.isEmpty()) {
            if (currentDistance == k) {
                break;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                // Left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    queue.offer(curr.left);
                }
                // Right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    queue.offer(curr.right);
                }
                // Parent
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }
            currentDistance++;
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }

        return result;
    }

    private void buildParentMap(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }
    }

    public static void main(String[] args) {
        All_Nodes_Distance_K_In_Binary_Tree solver = new All_Nodes_Distance_K_In_Binary_Tree();
        // Tree: 3 -> (5 -> (6, 2 -> (7, 4)), 1 -> (0, 8))
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        System.out.println("Nodes at distance 2 from node 5: " + solver.distanceK(root, node5, 2));
        // Expected: [7, 4, 1]
    }
}
