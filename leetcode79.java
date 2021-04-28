class leetcode79 {
	
	public static void main(String args[]) { 
        char[][] grids = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String testWord = "SEE";
        
        boolean flag = exist(grids, testWord);
        System.out.println(flag);
    } 
	
    public static boolean exist(char[][] board, String word) {
        //先找出所有首字母开头的位置
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (judgeBeThisWord(j, i, board, word)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    //转换状态矩阵值全为true or false
    public static void transformFlagMatrix(boolean[][] flags, boolean flag) {
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[0].length; j++) {
                flags[i][j] = flag;
            }
        }
    }

    //判断指定位置开始是否能够组成此单词
    public static boolean judgeBeThisWord(int posx, int posy, char[][] board, String word) {
        //DFS检索是否能够构成给定的单词
        boolean[][] flags = new boolean[board.length][board[0].length];
        //初始化状态矩阵为全false
        transformFlagMatrix(flags, false);

        
        position[] linkedlist = new position[word.length()];
        linkedlist[0] = new position();
        linkedlist[0].initPosition(posx, posy);
        flags[posy][posx] = true;
        int num = 1;

        //调用递归的深度优先搜索程序
        return find(board, flags, word, linkedlist, num);
    }

    //递归子程序 判断是否找到
    public static boolean find(char[][] board, boolean[][] flags, String word, position[] linkedlist, int num) {
        if (num == word.length()) {
            return true;
        }
        else {
            position curP = linkedlist[num-1];
            //上
            if (curP.curY > 0 && flags[curP.curY - 1][curP.curX] == false && board[curP.curY - 1][curP.curX] == word.charAt(num)) {
                flags[curP.curY - 1][curP.curX] = true;
                linkedlist[num] = new position();
                linkedlist[num].initPosition(curP.curX, curP.curY-1);
                num++;
                if (find(board, flags, word, linkedlist, num))
                	return true;
                //恢复
                num--;
                flags[curP.curY - 1][curP.curX] = false;
                linkedlist[num].initPosition(-1, -1);
            }
            //下
            if (curP.curY < board.length-1 && flags[curP.curY + 1][curP.curX] == false && board[curP.curY + 1][curP.curX] == word.charAt(num)) {
                flags[curP.curY + 1][curP.curX] = true;
                linkedlist[num] = new position();
                linkedlist[num].initPosition(curP.curX, curP.curY+1);
                num++;
                if (find(board, flags, word, linkedlist, num))
                	return true;
                //恢复
                num--;
                flags[curP.curY + 1][curP.curX] = false;
                linkedlist[num].initPosition(-1,-1);
            }
            //左
            if (curP.curX > 0 && flags[curP.curY][curP.curX - 1] == false && board[curP.curY][curP.curX - 1] == word.charAt(num)) {
                flags[curP.curY][curP.curX - 1] = true;
                linkedlist[num] = new position();
                linkedlist[num].initPosition(curP.curX-1, curP.curY);
                num++;
                if (find(board, flags, word, linkedlist, num))
                	return true;
                //恢复
                num--;
                flags[curP.curY][curP.curX - 1] = false;
                linkedlist[num].initPosition(-1,-1);
            }
            
            if (curP.curX < board[0].length-1 && flags[curP.curY][curP.curX+1] == false && board[curP.curY][curP.curX + 1] == word.charAt(num)) {
                //System.out.println(curP.curX + " " + curP.curY + " " + num + " " + word.charAt(num) + " " + board.length + " " + board[0].length); 
                flags[curP.curY][curP.curX + 1] = true;
                linkedlist[num] = new position();
                linkedlist[num].initPosition(curP.curX + 1, curP.curY);
                num++;
                if (find(board, flags, word, linkedlist, num))
                	return true;
                //恢复
                num--;
                flags[curP.curY][curP.curX + 1] = false;
                linkedlist[num].initPosition(-1,-1);
            }
        }
        return false;
    }

}

class position {
    int curX;
    int curY;
    public void initPosition (int x, int y) {
        curX = x;
        curY = y;
    }
}