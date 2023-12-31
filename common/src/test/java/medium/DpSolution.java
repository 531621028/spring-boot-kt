package medium;

public class DpSolution {

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        // int[] prices = new int[]{
        //     // 7, 1, 5, 3, 6, 4
        //     2, 3, 1, 1, 4
        // };
        // System.out.println(solution.canJump(prices));
        // int[][] grid = new int[][]{
        //     {1, 3, 1}, {1, 5, 1}, {4, 2, 1}
        // };
        // System.out.println(solution.minPathSum(grid));
        System.out.println(solution.minDistance("horse", "ros"));
        System.out.println(solution.minDistance("intention", "execution"));
        System.out.println(solution.minDistance("b", ""));
    }


    /**
     * 编辑距离
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    min = Math.min(min, dp[i - 1][j - 1]);
                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[m][n];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int pathSum = grid[m - 1][n - 1];
        for (int i = m - 2; i >= 0; i--) {
            pathSum = pathSum + grid[i][n - 1];
            grid[i][n - 1] = pathSum;
        }
        pathSum = grid[m - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            pathSum = pathSum + grid[m - 1][i];
            grid[m - 1][i] = pathSum;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                grid[i][j] = Math.min(grid[i + 1][j], grid[i][j + 1]) + grid[i][j];
            }
        }
        return grid[0][0];
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
