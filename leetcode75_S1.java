public class leetcode75_S1 {
    public static void sortColors(int[] nums) {
        int length = nums.length;
        int numRed = 0, numWhite = 0, numBlue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                numRed++;
            else if (nums[i] == 1)
                numWhite++;
            else if (nums[i] == 2)
                numBlue++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (numRed != 0) {
                nums[i] = 0;
                numRed--;
            }
            else if (numWhite != 0) {
                nums[i] = 1;
                numWhite--;
            }
            else {
                nums[i] = 2;
            }
        }
    }
    public static void main(String args[]) {
        int[] test = {2,0,2,1,1,0};
        sortColors(test);
        for (int i = 0; i < test.length; i++)
            System.out.println(test[i]);
    }

}
