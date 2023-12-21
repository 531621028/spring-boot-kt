package medium;

public class DpSolution {

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        // int[] prices = new int[]{
        //     // 7, 1, 5, 3, 6, 4
        //     2, 3, 1, 1, 4
        // };
        // System.out.println(solution.canJump(prices));
        System.out.println(solution.uniquePaths(3, 7));
    }


    /**
     * 不同的路径
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        int maxRight = 0;
        for (int i = 0; i < nums.length; i++) {
            // 前面跳跃所能达到的最大位置小于当前的位置，说明前面的跳跃达不到当前位置，就没有继续下去的必要了
            if (maxRight < i) {
                return false;
            }
            maxRight = Math.max(i + nums[i], maxRight);
            // 前面跳跃所能达到的最大位置大于等于数组长度，可以到达
            if (maxRight >= nums.length) {
                return true;
            }
        }
        return true;
    }


}
