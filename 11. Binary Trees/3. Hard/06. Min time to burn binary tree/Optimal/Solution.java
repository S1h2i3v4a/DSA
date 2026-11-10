/*
QUESTION:
Given a binary tree and a target node, find the minimum time required to burn the complete binary tree if the target node is set on fire.
In 1 unit of time, fire spreads from a burnt node to its connected parent, left child, and right child.

APPROACH:
- Step 1: Map child nodes to parent pointers using BFS and locate target node.
- Step 2: Burn outward in 3 directions (left, right, parent) using BFS with a `visited` set.
- Increment time at each level where at least one new neighbor burns.

TIME COMPLEXITY: O(N) two BFS passes.
SPACE COMPLEXITY: O(N) for parent map and visited set.
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private TreeNode mapParents(TreeNode root, Map<TreeNode, TreeNode> parentMap, int target) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode targetNode = null;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.val == target) targetNode = curr;

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

    public int minTime(TreeNode root, int target) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode targetNode = mapParents(root, parentMap, target);
        if (targetNode == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(targetNode);
        visited.add(targetNode);

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burnedAny = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    queue.offer(curr.left);
                    burnedAny = true;
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    queue.offer(curr.right);
                    burnedAny = true;
                }
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                    burnedAny = true;
                }
            }
            if (burnedAny) time++;
        }
        return time;
    }
}
