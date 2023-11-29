import java.util.List;

public class TreeSolution {

    public List<Integer> inorderTraversal(TreeNode root) {
        return null;
    }

    public static void main(String[] args) {
        TreeSolution solution = new TreeSolution();
        TreeNode root = new TreeNode();
        System.out.println(solution.inorderTraversal(root));
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
