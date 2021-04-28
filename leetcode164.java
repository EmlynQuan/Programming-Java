public class leetcode164 {
    public int maximumGap(int[] nums) {
        int length = nums.length, max = 0, min = Integer.MAX_VALUE;
        if(length < 2)
            return 0;
        //找到最大最小值
        for (int num: nums) {
            max = Math.max(max,num);
            min = Math.min(min,num);
        }
        //如果最大值等于最小值特殊处理
        if(min == max)
            return 0;

        int bs = Math.max(1,(max-min)/(length-1)),bn = (max-min)/bs + 1;
        //记录桶内的最大值最小值
        int bucket[][] = new int[bn][2];

        for(int i = 0; i < bn; i++) {
            bucket[i][0] = Integer.MAX_VALUE;
            bucket[i][1] = -1;
        }
        for(int num: nums) {
            //更新桶内的最大最小值
            int p = (num-min)/bs;
            bucket[p][0] = Math.min(bucket[p][0], num);
            bucket[p][1] = Math.max(bucket[p][1], num);
        }
        int res = 0;
        int now = 0;
        while(bucket[now][0] == Integer.MAX_VALUE && bucket[now][1] == -1)
            now++;
        int left = bucket[now][1], right = 0;
        //找到桶之间的最大值
        now++;
        while(now < bn){
            if(bucket[now][0] == Integer.MAX_VALUE && bucket[now][1] == -1) {
                now++;
            }
            else {
                right = bucket[now][0];
                res = Math.max(res, right-left);
                left = bucket[now][1];
                now++;
            }
        }
        return res;
    }
}
