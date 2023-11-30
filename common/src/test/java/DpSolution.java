public class DpSolution {

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

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        int[] prices = new int[]{
            // 7, 1, 5, 3, 6, 4
            7, 6, 4, 3, 1
        };
        System.out.println(solution.maxProfit(prices));
    }
}
