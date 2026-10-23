/*
Question: Given the root of a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.
*/

import java.io.*;
import java.util.*;

// CODE:-
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }
    private void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) return;
        if (currDepth == result.size()) result.add(curr.val);
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
