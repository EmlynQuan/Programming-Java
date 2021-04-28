import java.util.ArrayList;
import java.util.List;

public class leetcode19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] listNodes = new ListNode[n];
        ListNode cur = new ListNode(Integer.MAX_VALUE);
        cur.next = head;
        int pos = 0, num = 0;
        if (cur.next == null)
            return null;
        while (cur != null) {
            num++;
            if (cur.next != null) {
                listNodes[pos] = cur;
                pos = (pos+1)%n;
            }
            cur = cur.next;
        }
        listNodes[pos].next = listNodes[pos].next.next;
        //System.out.println(n + " " + num);
        if (n == num-1)
            return head.next;
        return head;
    }

    public static void main(String args[]) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(10);
        head = removeNthFromEnd(head, 2);
        while (head != null){
            System.out.println(head.val);
            head =head.next;
        }
    }
}

/*
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

 */