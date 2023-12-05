package easy;

import java.util.Arrays;

public class BitSolution {

    public static void main(String[] args) {
        BitSolution bitSolution = new BitSolution();
        int[] nums = new int[]{
            // 7, 1, 5, 3, 6, 4
            4, 1, 2, 1, 2
        };
        System.out.println(bitSolution.singleNumber(nums));
        System.out.println(bitSolution.hammingDistance(1, 4));
        System.out.println(bitSolution.hammingDistance(1, 3));
        System.out.println(Arrays.toString(bitSolution.countBits(2)));
        System.out.println(Arrays.toString(bitSolution.countBits(5)));
    }

    /**
     * 两个数字对应二进制位不同的位置的数目
     */
    public int hammingDistance(int x, int y) {
        int num = x ^ y;
        int cnt = 0;
        while (num != 0) {
            cnt++;
            num = num & (num - 1);
        }
        return cnt;
    }

    /**
     * 比特为计数， 对于任意整数 x，令 x=x & (x−1)，该运算将 x 的二进制表示的最后一个 1 变成 0
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int cnt = 0, j = i;
            while (j != 0) {
                cnt++;
                j = j & (j - 1);
            }
            result[i] = cnt;
        }
        return result;
    }

    /**
     * 异或运算有一个性质：任何数和自己做异或运算的结果为 0。同时，任何数和 0 做异或运算的结果都是它本身。
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
