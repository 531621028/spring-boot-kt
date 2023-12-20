package medium;

public class DpSolution {

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        int[] prices = new int[]{
            // 7, 1, 5, 3, 6, 4
            2, 3, 1, 1, 4
        };
        System.out.println(solution.canJump(prices));
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
