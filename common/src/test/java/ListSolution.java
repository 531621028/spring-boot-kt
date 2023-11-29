public class ListSolution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 新建一个头节点，只标记，无实际作用
        ListNode result = new ListNode();
        // 头节点的遍历对象
        ListNode p = result;
        ListNode p1, p2;
        p1 = list1;
        p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        // 返回标记节点的后一个节点
        return result.next;
    }

    public static void main(String[] args) {
        ListSolution solution = new ListSolution();
        ListNode list1 = solution.buildNode(new int[]{
            1
        });
        ListNode list2 = solution.buildNode(new int[]{
            2
        });
        ListNode listNode = solution.mergeTwoLists(list1, list2);
        solution.print(listNode);
    }

    private void print(ListNode list) {
        StringBuilder sb = new StringBuilder();
        while (list != null) {
            sb.append(list.val).append(",");
            list = list.next;
        }
        System.out.println(sb);
    }

    private ListNode buildNode(int[] nums) {
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

    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}