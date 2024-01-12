package dp.two_sum;

/**
 * Definition for singly-linked list.
 * public class dp.add2nums.ListNode {
 *     int val;
 *     dp.add2nums.ListNode next;
 *     dp.add2nums.ListNode() {}
 *     dp.add2nums.ListNode(int val) { this.val = val; }
 *     dp.add2nums.ListNode(int val, dp.add2nums.ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node = null;
        ListNode startNode = null;
        int carry = 0;
        for(ListNode i = l1,  j = l2; i != null && j != null ; i = i.next, j = j.next){
            int res = i.val + j.val + carry;
            carry = res / 10;
            if(node == null){
                startNode = new ListNode(res % 10);
                node = startNode;
            } else {
                node.next = new ListNode(res % 10);
                node = node.next;
            }
        }


        return startNode;
    }

    public static void main(String[] args) {
        var solution = new Solution();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        System.out.println("List 1 : " + l1);
        System.out.println("List 2 : " + l2);
        ListNode l3 = solution.addTwoNumbers(l1 ,l2);
        System.out.println("Result : " + l3);

    }
}