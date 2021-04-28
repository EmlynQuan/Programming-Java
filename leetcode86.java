class leetcode86 {
	public static void main(String args[]) { 
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(2);
		
		ListNode result = partition(head, 3);
        while (result != null) {
        	System.out.println(result.val + ", ");
        	result = result.next;
        }
    } 
	
    public static ListNode partition(ListNode head, int x) {
    	if (head == null)
    		return head;
    	ListNode small = null, big = null, smallHead = null, bigHead = null;
    	boolean flagSmall = true, flagBig = true;
    	while (head != null) {
    		//System.out.println("cur + " + head.val);
    		//是小的指针列表
    		if (head.val < x) {
    			if (flagSmall) {
    				small = new ListNode(head.val);
    				smallHead = small;
    				flagSmall = false;
    			}
    			else {
    				small.next = new ListNode(head.val);
    				small = small.next;
    			}
    		}
    		//是大的指针列表
    		else {
    			if (flagBig) {
    				big = new ListNode(head.val);
    				bigHead = big;
    				flagBig = false;
    			}
    			else {
    				big.next = new ListNode(head.val);
    				big = big.next;
    			}
    		}
    		head = head.next;
    	}
    	
    	if (smallHead == null) {
    		return bigHead;
    	}
    	else if (bigHead == null) {
    		return smallHead;
    	}
    	else {
    		small.next = bigHead;
    	}
        return smallHead;
    }
}


class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}