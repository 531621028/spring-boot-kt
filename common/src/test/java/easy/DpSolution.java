package easy;

public class DpSolution {

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        int[] prices = new int[]{
            // 7, 1, 5, 3, 6, 4
            7, 6, 4, 3, 1
        };
        System.out.println(solution.maxProfit(prices));
    }

    /**
     * 买卖股票最佳时机
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length <= 1) {
            return result;
        }
        int[] min = new int[prices.length];
        min[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int leftMin = min[i - 1];
            int curPrice = prices[i];
            if (curPrice > leftMin) {
                min[i] = leftMin;
                int gain = curPrice - leftMin;
                result = Math.max(result, gain);
            } else {
                min[i] = curPrice;
            }
        }
        return result;
    }

    /**
     * 爬楼梯
     */
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

}
