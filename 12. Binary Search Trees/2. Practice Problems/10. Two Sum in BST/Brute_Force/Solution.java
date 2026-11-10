/*
QUESTION:
Given the root of a binary search tree and an integer `k`, return true if there exist two elements in the BST
such that their sum is equal to `k`, or false otherwise.

APPROACH:
- Perform an inorder traversal and store elements in a sorted list.
- Use standard two pointers `left = 0` and `right = list.size() - 1` to find pair summing to `k`.

TIME COMPLEXITY: O(N) inorder + two pointers.
SPACE COMPLEXITY: O(N) list storage.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int left = 0, right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) return true;
            if (sum < k) left++;
            else right--;
        }
        return false;
    }
}
