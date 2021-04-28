import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class leetcode78_S2 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsetList = new ArrayList<>();

        //添加空集
        List<Integer> nullSet = new ArrayList<>();
        subsetList.add(nullSet);

        //从length为1的子结构开始求解
        for (int i = 0; i< nums.length; i++) {
            int counter = subsetList.size();
            for (int j = 0; j < counter; j++) {
                List<Integer> temp = new ArrayList<>(subsetList.get(j));
                temp.add(nums[i]);
                subsetList.add(temp);
            }
        }

        for (int i = 0; i < subsetList.size(); i++) {
            System.out.println(subsetList.get(i).toString());
        }
        return subsetList;
    }

    public static void main(String args[]) {
        int[] test = {1,2,3};
        subsets(test);
    }


}
