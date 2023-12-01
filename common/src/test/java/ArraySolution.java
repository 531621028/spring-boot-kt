import java.util.HashMap;
import java.util.Map;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        int[] prices = new int[]{
            // 7, 1, 5, 3, 6, 4
            7, 6, 4, 3, 1
        };
        System.out.println(solution.majorityElement(prices));
    }

    /**
     * 多数元素
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> numCntMap = new HashMap<>();
        int maxCnt = 0;
        int maxNum = 0;
        for (int num : nums) {
            int cnt = numCntMap.getOrDefault(num, 0) + 1;
            numCntMap.put(num, cnt);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                maxNum = num;
            }
        }
        return maxNum;
    }

}
