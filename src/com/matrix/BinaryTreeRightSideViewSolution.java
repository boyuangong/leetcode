package com.matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author boyuangong created on 12/3/20 at 23:25
 */
public class BinaryTreeRightSideViewSolution {
    List<Integer> ans = new ArrayList<>();
    Set<TreeNode> visited = new HashSet<>();

    public List<Integer> rightSideView(TreeNode root) {
        // always start from the right node, if it hit the next
        // level, we add it to the list, repeat to the left node
        // until we went through the whole tree

        findNextLevelRight(root, 0);
        return ans;

    }

    private void findNextLevelRight(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == ans.size()) {
            ans.add(root.val);
        }
        visited.add(root);
        findNextLevelRight(root.right, level + 1);
        findNextLevelRight(root.left, level + 1);
    }
}
