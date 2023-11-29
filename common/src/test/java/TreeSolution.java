import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TreeSolution {


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

    public static void main(String[] args) {
        TreeSolution solution = new TreeSolution();
        TreeNode root = buildTreeNode(new Integer[]{
            // 1, 2, 2, null, 3, null, 3
            1, 2, 2, 3, 4, 4, 3
        });
        // System.out.println(Arrays.toString(solution.inorderTraversal(root).toArray()));
        System.out.println(solution.isSymmetric(root));
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
    }
}
