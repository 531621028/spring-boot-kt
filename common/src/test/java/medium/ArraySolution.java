package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        int[] arrays = new int[]{
            // -1, 0, 1, 2, -1, -4
            // 3,-2,1,0
            // -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4
            // -2, 0, 0, 2, 2
            34, 55, 79, 28, 46, 33, 2, 48, 31, -3, 84, 71, 52, -3, 93, 15, 21, -43, 57, -6, 86, 56, 94, 74, 83, -14, 28, -66, 46, -49, 62,
            -11, 43, 65, 77, 12, 47, 61, 26, 1, 13, 29, 55, -82, 76, 26, 15, -29, 36, -29, 10, -70, 69, 17, 49
        };
        // System.out.println(solution.maxArea(arrays));
        System.out.println(Arrays.toString(solution.threeSum(arrays).toArray()));
    }


    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(buildList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left++] == nums[left]) {
                    }
                    while (left < right && nums[right--] == nums[right]) {
                    }
                }
                if (sum < 0) {
                    left++;
                }
                if (sum > 0) {
                    right--;
                }
            }
        }
        return result;
    }

    private List<Integer> buildList(int num, int num1, int num2) {
        List<Integer> list = new ArrayList<>();
        list.add(num);
        list.add(num1);
        list.add(num2);
        return list;
    }


    /**
     * 盛水最多的容器
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, maxArea = 0;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            int area = h * (j - i);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return maxArea;
    }

}
