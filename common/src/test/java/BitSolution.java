public class BitSolution {

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

    public static void main(String[] args) {
        BitSolution bitSolution = new BitSolution();
        int[] nums = new int[]{
            // 7, 1, 5, 3, 6, 4
            4, 1, 2, 1, 2
        };
        System.out.println(bitSolution.singleNumber(nums));
    }
}
