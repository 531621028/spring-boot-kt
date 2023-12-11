package medium;

import data.ListNode;

public class ListSolution {


    public static void main(String[] args) {
        ListNode l1 = ListNode.buildNode(new int[]{
            1, 2, 3, 4, 5
        });
        // ListNode l2 = ListNode.buildNode(new int[]{
        //     9, 9, 9, 9
        // });
        ListSolution solution = new ListSolution();
        ListNode.print(solution.removeNthFromEnd(l1, 2));
    }


    /**
     * 删除链表的倒数第N个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        int j = n;
        while (j > 0) {
            fast = fast.next;
            j--;
        }
        if (fast == null) {
            return head.next;
        }
        ListNode low = head;
        while (fast.next != null) {
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        return head;
    }

    /**
     * 两数相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode p = result;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int var = l1.val + l2.val + carry;
            if (var >= 10) {
                p.next = new ListNode(var - 10);
                carry = 1;
            } else {
                p.next = new ListNode(var);
                carry = 0;
            }
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int var = l1.val + carry;
            if (var >= 10) {
                p.next = new ListNode(var - 10);
                carry = 1;
            } else {
                p.next = new ListNode(var);
                carry = 0;
            }
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int var = l2.val + carry;
            if (var >= 10) {
                p.next = new ListNode(var - 10);
                carry = 1;
            } else {
                p.next = new ListNode(var);
                carry = 0;
            }
            p = p.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            p.next = new ListNode(carry);
        }
        return result.next;
    }

}
