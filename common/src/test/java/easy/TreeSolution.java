package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TreeSolution {

    public static void main(String[] args) {
        TreeSolution solution = new TreeSolution();
        TreeNode root = buildTreeNode(new Integer[]{
            1,2
        });
        // TreeNode root2 = buildTreeNode(new Integer[]{
        //     2, 1, 3, null, 4, null, 7
        // });
        // System.out.println(Arrays.toString(solution.inorderTraversal(root).toArray()));
        // System.out.println(solution.isSymmetric(root));
        // solution.printTree(solution.mergeTrees(root, root2));
        System.out.println(solution.diameterOfBinaryTree(root));
    }


    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthLeft = 0;
        int depthRight = 0;
        int diameterLeft = 0;
        int diameterRight = 0;
        if (root.left != null) {
            depthLeft = treeLevel(root.left);
            diameterLeft = diameterOfBinaryTree(root.left);
        }
        if (root.right != null) {
            depthRight = treeLevel(root.right);
            diameterRight = diameterOfBinaryTree(root.right);
        }
        int diameterRoot = depthLeft + depthRight;
        int diameter = Math.max(diameterLeft, diameterRight);
        return Math.max(diameter, diameterRoot);
    }

    /**
     * 合并二叉树
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    /**
     * 反转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        int maxLevel = 0;
        while (!deque.isEmpty()) {
            maxLevel++;
            Deque<TreeNode> tempQueue = new LinkedList<>();
            deque.forEach(treeNode -> {
                if (treeNode.left != null) {
                    tempQueue.add(treeNode.left);

                }
                if (treeNode.right != null) {
                    tempQueue.add(treeNode.right);
                }
            });
            deque.clear();
            deque.addAll(tempQueue);
        }
        return maxLevel;
    }


    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.right == null && root.left == null) {
            return true;
        } else if (root.right == null || root.left == null) {
            return false;
        }
        Deque<TreeNode> leftQueue = new LinkedList<>();
        Deque<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.add(root.left);
        rightQueue.add(root.right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode first = leftQueue.removeFirst();
            TreeNode last = rightQueue.removeLast();
            if (first.val == last.val) {
                if (first.left != null) {
                    leftQueue.addFirst(first.left);
                }
                if (last.right != null) {
                    rightQueue.addLast(last.right);
                }

                if (last.left != null) {
                    leftQueue.addFirst(last.left);
                }
                if (first.right != null) {
                    rightQueue.addLast(first.right);
                }
            } else {
                return false;
            }
        }
        return leftQueue.isEmpty() && rightQueue.isEmpty();
    }

    /**
     * 二叉树中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        Set<TreeNode> readNode = new HashSet<>();
        while (!deque.isEmpty()) {
            TreeNode node = deque.getLast();
            if (node.left != null && !readNode.contains(node)) {
                deque.addLast(node.left);
            } else {
                deque.removeLast();
                result.add(node.val);
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
            readNode.add(node);
        }
        return result;
    }

    private static TreeNode buildTreeNode(Integer[] nodes) {
        if (nodes == null || nodes.length <= 0) {
            return null;
        }
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }
            if (treeNodes[i] == null) {
                treeNodes[i] = new TreeNode(nodes[i]);
            }
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < nodes.length && nodes[left] != null) {
                TreeNode leftNode = new TreeNode(nodes[left]);
                treeNodes[i].left = leftNode;
                treeNodes[left] = leftNode;
            }
            if (right < nodes.length && nodes[right] != null) {
                TreeNode rightNode = new TreeNode(nodes[right]);
                treeNodes[i].right = rightNode;
                treeNodes[right] = rightNode;
            }
        }
        return treeNodes[0];
    }

    private void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Integer[] var = new Integer[(2 << treeLevel(root) - 1) - 1];
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int loc = 0;
        while (!deque.isEmpty()) {
            if (loc >= var.length) {
                break;
            }
            TreeNode node = deque.removeFirst();
            var[loc] = node.val == Integer.MIN_VALUE ? null : node.val;
            if (node.left != null) {
                deque.add(node.left);
            } else {
                deque.add(TreeNode.NULL);
            }
            if (node.right != null) {
                deque.add(node.right);
            } else {
                deque.add(TreeNode.NULL);
            }
            loc++;
        }
        System.out.println(Arrays.toString(var));
    }

    private int treeLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int level = 0;
        while (!deque.isEmpty()) {
            level++;
            Deque<TreeNode> nextLevel = new LinkedList<>();
            while (!deque.isEmpty()) {
                TreeNode node = deque.removeFirst();
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            deque = nextLevel;
        }
        return level;

    }

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static TreeNode NULL = new TreeNode(Integer.MIN_VALUE);
    }
}
