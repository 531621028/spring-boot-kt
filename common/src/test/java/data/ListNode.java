package data;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public static ListNode buildNode(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        ListNode root = new ListNode(nums[0]);
        ListNode p = root;
        for (int i = 1; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        return root;
    }


    public static void print(ListNode list) {
        StringBuilder sb = new StringBuilder();
        while (list != null) {
            sb.append(list.val).append(",");
            list = list.next;
        }
        System.out.println(sb);
    }
}
