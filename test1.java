class test1 {
    public int[] intersect(int[] nums1, int[] nums2) {
    	boolean[] flag1 = new boolean[nums1.length];
    	boolean[] flag2 = new boolean[nums2.length];
    	int counter = 0;
        for (int i = 0; i < nums1.length; i++) {
        	if (flag1[i] == false) {
        		for (int j = 0; j < nums2.length; j++) {
            		if (flag2[j] == false && nums1[i] == nums2[j]) {
            			flag1[i] = true;
            			flag2[j] = true;
            			counter++;
            			break;
            		}
            	}
        	}
        }
        int[] result = new int[counter];
        int pos = 0;
        if (nums1.length < nums2.length) {
        	for (int i = 0; i < nums1.length; i++) {
        		if (flag1[i] == true) {
        			result[pos++] = nums1[i];
        		}
        	}
        }
        else {
        	for (int i = 0; i < nums2.length; i++) {
        		if (flag2[i] == true) {
        			result[pos++] = nums2[i];
        		}
        	}
        }
        return result;
    }
}