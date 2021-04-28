class leetcode42_S2 {
	//动态规划
    public int trap(int[] height) {
    	int sum = 0;
    	int[] max_left = new int[height.length];
    	int[] max_right = new int[height.length];
    	
    	//计算max_left
    	for (int i = 1; i < height.length; i++) {
    		max_left[i] = Math.max(max_left[i-1], height[i-1]);
    	}
    	//计算max_right
    	for (int i = height.length-2; i >= 0; i--) {
    		max_right[i] = Math.max(max_right[i+1], height[i+1]);
    	}
    	//当前列
    	for (int i = 0; i < height.length; i++) {
    		int temp = Math.min(max_left[i], max_right[i]);
    		if (temp > height[i]) {
    			sum += temp-height[i];
    		}
    	}
    	return sum;
    }
}