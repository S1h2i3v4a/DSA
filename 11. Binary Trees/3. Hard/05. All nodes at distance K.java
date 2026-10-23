/* QUESTION:

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.


APPROACH:

To find the nodes that are at a distance k from the target node, we can perform a two-step process:
1. First, traverse the binary tree to build a map of each node to its parent node using BFS. This map will help us later to backtrack from the target node to its ancestors.
2. Next, perform a BFS from the target node to find all nodes at a distance k from it. While doing this, we also mark visited nodes to avoid revisiting the same nodes.

COMPLEXITY ANALYSIS:

Let `n` be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n) since we need to traverse the entire binary tree to build the parent map and perform BFS from the target node.
- Space Complexity: The space complexity is O(n) for the parent map and O(k) for the queue used in BFS. In the worst case, when k approaches n, the space complexity becomes O(n).

CODE:
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
