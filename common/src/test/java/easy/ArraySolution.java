package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        // int[] prices = new int[]{
        //     // 7, 1, 5, 3, 6, 4
        //     7, 6, 4, 3, 1
        // };
        // System.out.println(solution.majorityElement(prices));
        int[] nums = new int[]{
            // 0, 1, 0, 3, 12
            // 1, 0, 1
            1, 1
        };
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(solution.findDisappearedNumbers(nums).toArray()));
    }


    /**
     * 数组中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] record = new int[nums.length];
        for (int num : nums) {
            record[num - 1] = 1;
        }
        for (int i = 0; i < record.length; i++) {
            if (record[i] == 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /**
     * 移动零
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
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
