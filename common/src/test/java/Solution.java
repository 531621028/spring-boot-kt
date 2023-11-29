public class Solution {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] climb = new int[n];
        climb[0] = 1;
        climb[1] = 2;
        for (int i = 2; i < n; i++) {
            climb[i] = climb[i - 1] + climb[i - 2];
        }
        return climb[n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(3));
    }
}
