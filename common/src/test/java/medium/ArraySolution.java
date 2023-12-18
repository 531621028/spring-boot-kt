package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        // int[] arrays = new int[]{
        // -1, 0, 1, 2, -1, -4
        // 3,-2,1,0
        // -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4
        // -2, 0, 0, 2, 2
        //     34, 55, 79, 28, 46, 33, 2, 48, 31, -3, 84, 71, 52, -3, 93, 15, 21, -43, 57, -6, 86, 56, 94, 74, 83, -14, 28, -66, 46, -49, 62,
        //     -11, 43, 65, 77, 12, 47, 61, 26, 1, 13, 29, 55, -82, 76, 26, 15, -29, 36, -29, 10, -70, 69, 17, 49
        // };
        // System.out.println(solution.maxArea(arrays));
        // System.out.println(Arrays.toString(solution.threeSum(arrays).toArray()));
        int[] nums = new int[]{
            2, 3, 6, 7
        };
        // solution.nextPermutation(nums);
        // System.out.println(Arrays.toString(nums));
        // System.out.println(solution.search(nums, 9));
        // System.out.println(Arrays.toString(solution.searchRange(nums, 6)));
        System.out.println(Arrays.toString(solution.combinationSum(nums, 8).toArray()));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        dfsCombination(candidates, 0, target, new LinkedList<>(), result);
        return result;
    }

    public void dfsCombination(int[] candidates, int start, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            dfsCombination(candidates, i, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    /**
     * 搜索第一个和最后一个的位置
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        return searchRange(nums, 0, nums.length - 1, target);
    }

    public int[] searchRange(int[] nums, int start, int end, int target) {
        if (start == end && nums[start] != target) {
            return new int[]{-1, -1};
        }
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return findRange(nums, mid, target);
        } else if (nums[mid] > target) {
            return searchRange(nums, start, mid, target);
        } else {
            return searchRange(nums, mid + 1, end, target);
        }
    }

    private int[] findRange(int[] nums, int find, int target) {
        int i = find;
        while (i < nums.length && nums[i] == target) {
            i++;
        }
        int j = find;
        while (j >= 0 && nums[j] == target) {
            j--;
        }
        return new int[]{j + 1, i - 1};
    }

    /**
     * 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    int search(int[] nums, int start, int end, int target) {
        if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return -1;
            }
        }
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            if (nums[mid] < nums[end] || nums[start] <= target) {
                return search(nums, start, mid, target);
            } else {
                return search(nums, mid + 1, end, target);
            }
        } else if (nums[mid] < target) {
            if (target <= nums[end] || nums[start] < nums[mid]) {
                return search(nums, mid + 1, end, target);
            } else {
                return search(nums, start, mid, target);
            }
        } else {
            return -1;
        }
    }


    /**
     * 下一个排列
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int minIndex = -1;
        int minGrater = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    if (nums[j] < minGrater) {
                        minGrater = nums[j];
                        minIndex = j;
                    }
                }
            }
            if (minIndex > 0) {
                swap(nums, i, minIndex);
                Arrays.sort(nums, i + 1, nums.length);
                return;
            }

        }
        Arrays.sort(nums);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
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
