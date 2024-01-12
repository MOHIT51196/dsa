package dp.two_sum;

import java.util.ArrayList;

public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      @Override
      public String toString() {
            ArrayList<Integer> list = new ArrayList<>();
            for(ListNode n = this; n != null; n = n.next) list.add(n.val);
            return list.toString();
      }
}