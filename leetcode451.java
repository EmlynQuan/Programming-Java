class leetcode451 {
    public String frequencySort(String s) {
    	int[] freq = new int[128];
		for (int i = 0; i < 128; i++) {
			freq[i] = 0;
		}
		
    	for (int i = 0; i < s.length(); i++) {
    		int t = s.charAt(i);
			freq[t]++;
    	}
    	value head = new value();
    	head.pos = 0;
    	head.val = 0;
    	boolean flag = true;
    	value list = head;
    	while (flag) {
    		flag = false;
    		for (int i = 0; i < 128; i++) {
        		if (freq[i] > head.val) {
        			flag = true;
        			head.pos = i;
        			head.val = freq[i];
        		}
        	}
    		freq[head.pos] = -1;
    		head.next = new value();
    		head = head.next;
    		head.pos = 0;
        	head.val = 0;
    	}
    	
    	String result = "";
    	
    	if (list == null) {
    		return result;
    	}
    	
    	
    	while (list != null) {
    		for (int i = 0; i < list.val; i++) {
    			result += (char)(list.pos);
    		}
    		list = list.next;
    	}
    	return result;
    }
}

class value {
	int pos;
	int val;
	value next;
}