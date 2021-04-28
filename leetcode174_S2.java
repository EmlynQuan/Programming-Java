import java.util.Arrays;

public class leetcode174_S2 {
    public static int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        //动态规划辅助数组
        int[][] dp = new int[row+1][col+1];
        //初始化
        for (int i = 0; i < row+1; i++)
            dp[i][col] = Integer.MAX_VALUE;
        for (int j = 0; j < col+1; j++)
            dp[row][j] = Integer.MAX_VALUE;
        dp[row][col-1] = 1;
        dp[row-1][col] = 1;
        //开始遍历
        for (int i = row-1; i >= 0; i--)
            for (int j = col-1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i][j+1], dp[i+1][j]);
                dp[i][j] = Math.max(1, dp[i][j]-dungeon[i][j]);
            }
        //System.out.println(dp[0][0]);
        return dp[0][0];
    }

    public static void main(String args[]) {
        int[][] test = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        calculateMinimumHP(test);
    }
}
