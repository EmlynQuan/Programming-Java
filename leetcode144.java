import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }
//递归
public class leetcode144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        travTree(result, root);
        return result;
    }
    public void travTree(List<Integer> integerList, TreeNode root) {
        if (root == null) {
            return;
        }
        else {
            integerList.add(root.val);
            travTree(integerList, root.left);
            travTree(integerList, root.right);
        }
    }
}


