/*
QUESTION:
Given below is a binary tree. The task is to print the top view of binary tree.
The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.

APPROACH:
- Perform BFS level order traversal tracking horizontal distance `hd` from root (`hd = 0`).
- Left child has `hd - 1`, right child has `hd + 1`.
- Maintain a `TreeMap<Integer, Integer>` mapping `hd -> node.val`.
- Store only the first node seen at each `hd`.

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
    public ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node.val);
            }
            if (curr.node.left != null) queue.offer(new Pair(curr.node.left, curr.hd - 1));
            if (curr.node.right != null) queue.offer(new Pair(curr.node.right, curr.hd + 1));
        }

        result.addAll(map.values());
        return result;
    }
}
