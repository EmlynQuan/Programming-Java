import java.util.ArrayList;
import java.util.List;

public class leetcode17_22 {
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> result = new ArrayList<>();
        //如果是空
        if (!wordList.contains(beginWord)) {
            return result;
        }
        if (wordList.contains(endWord)) {
            return result;
        }
        //可以找到
        
        return result;
    }
}
