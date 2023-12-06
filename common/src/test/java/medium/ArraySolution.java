package medium;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        int[] arrays = new int[]{
            1, 8, 6, 2, 5, 4, 8, 3, 7
            // 1, 1
        };
        System.out.println(solution.maxArea(arrays));
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
