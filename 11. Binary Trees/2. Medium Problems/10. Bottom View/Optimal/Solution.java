/*
QUESTION:
Given a binary tree, print the bottom view from left to right.
A node is included in bottom view if it can be seen when we look at the tree from bottom.

APPROACH:
- Perform BFS tracking horizontal distance `hd`.
- Store mapping `map.put(hd, node.val)`.
- Since BFS traverses level-by-level, later visited nodes overwrite earlier ones at the same `hd`, leaving the bottom-most node.

TIME COMPLEXITY: O(N log N) using TreeMap.
SPACE COMPLEXITY: O(N) for queue and map.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Pair {
    TreeNode node;
    int hd;
    Pair(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

class Solution {
    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            map.put(curr.hd, curr.node.val);

            if (curr.node.left != null) queue.offer(new Pair(curr.node.left, curr.hd - 1));
            if (curr.node.right != null) queue.offer(new Pair(curr.node.right, curr.hd + 1));
        }

        result.addAll(map.values());
        return result;
    }
}
