//简单的基于列进行求解
class leetcode42_S1 {
    public int trap(int[] height) {
        int sum = 0, max_left = 0, max_right = 0;
        //当前列
        for (int i = 0; i < height.length; i++) {
            max_left = 0;
            max_right = 0;
            //左边最大
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            //右边最大
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            
            //木桶效应 取决于更低的值
            int temp = Math.min(max_left, max_right);
            //如果当前列是更矮的
            if (temp > height[i]) {
                sum += temp - height[i];
            }
        }
        return sum;
    }
}