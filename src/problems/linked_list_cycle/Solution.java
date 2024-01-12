package problems.linked_list_cycle;

 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
 }

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        // using Floyd cycle algorithm
        ListNode sp = head; // slow pointer
        ListNode fp = head.next; // fast pointer
        
        while(sp != null && fp != null && fp.next != null){
            if(sp == fp) return true;
            sp = sp.next;
            fp = fp.next.next;
        }
        return false;
        
    }
}