import java.io.*;
import java.util.*;

public class leetcode78_S1 {
    public static boolean twoListCompareUtil(List<Integer> firstList, List<Integer> scList) {
        //首先判断这两个list的大小是否相等
        if(firstList.size() != scList.size()){
            return false;
        }
        //如果两个list大小相等，其中可能为0，所以排除为0的这种情况
        if(firstList.size()>0 && scList.size()>0){
            //循环遍历两个几个取值比较是否相同，不同则返回false
            for(int i=0; i<firstList.size(); i++){
                //取值比较是否相同
                if(firstList.get(i) != (scList.get(i))){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }

    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsetList = new ArrayList<>();

        //添加空集
        List<Integer> nullSet = new ArrayList<>();
        subsetList.add(nullSet);

        List<List<Integer>> temp = new ArrayList<>();
        temp.add(nullSet);
        int counter = nums.length;
        //从个数为1，逐个添加，直到个数为nums.length
        while (temp.size() != 0 && counter != 0) {
            int size = temp.size();
            for (int i = 0; i < size; i++) {
                List<Integer> loop = new ArrayList<>(temp.get(i));

                for (int j = 0; j < nums.length; j++) {
                    if (!loop.contains(nums[j])) {
                        loop.add(nums[j]);
                        List<Integer> addElement = new ArrayList<>(loop);
                        Collections.sort(addElement);

                        boolean flag = true;
                        for (int t = 0; t < temp.size(); t++) {
                           if (twoListCompareUtil(temp.get(t), addElement)) {
                               flag = false;
                               break;
                           }
                        }
                        if (flag) {
                            temp.add(addElement);
                        }

                        loop.remove(loop.size() - 1);
                    }
                }
            }

            //update
            for (int i = size; i < temp.size(); i++) {
                subsetList.add(temp.get(i));
            }

            if (counter == nums.length) {
                temp.remove(0);
            }
            else {
                for (int i = 0; i < size; i++) {
                    temp.remove(0);
                }
            }

            counter--;
        }

        //输出结果
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
