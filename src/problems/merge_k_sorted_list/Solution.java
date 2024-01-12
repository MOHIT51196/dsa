package problems.merge_k_sorted_list;

import java.util.*;


class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {this.val = val;}

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode node = null;
        ListNode start = null;
        while(true){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            int exhusted = 0;
            for (int k = 0; k < lists.length; k++) {
                if(lists[k] == null) {
                    exhusted++;
                    continue;
                }

                if(lists[k].val < min) {
                    min = lists[k].val;
                    minIndex = k;
                }
            }

            // System.out.println("exhausted = " + exhusted);
            if(exhusted >= lists.length) break;

            lists[minIndex] = lists[minIndex].next;
            // System.out.println("New min = " + min + " from node " + minIndex);

            if(node == null) {
                node = new ListNode(min);
                start = node;
            }
            else {
                node.next = new ListNode(min);
                node = node.next;
            }
        }
        return start;
    }
}