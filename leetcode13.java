import java.util.ArrayList;
import java.util.HashMap;

public class leetcode13 {
    public int minimalSteps(String[] maze) {
        char[][] grid = new char[maze.length][maze[0].length()];
        for (int i = 0; i < maze.length; i++) {
            grid[i] = maze[i].toCharArray();
        }
        int stepCounter = 0;
        //先找距离每个M最近的O 为机关寻找石块
        HashMap<Position, Position> map = new HashMap<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length(); j++) {
                //机关
                if (grid[i][j] == 'M') {
                    //广度优先搜索最近的石块
                    Position temp = Bfs(grid, i, j);
                    if (temp.x == -1)
                        return -1;
                    else {
                        map.put(new Position(i, j, 0), temp);
                        stepCounter += 2*temp.val;
                    }
                }
            }
        }

        //找从S开始 穿过所有M 的到达T的路径的长度 并记录途中从O到M的最短距离


        return 0;
    }

    public Position Bfs (char[][] grid, int m, int n) {
        ArrayList<Position> neib = new ArrayList<>();
        int[] addX = {0, -1, 0, 1};
        int[] addY = {-1, 0, 1, 0};
        //上->左->下->右
        for (int i = 0; i < addX.length; i++) {
            if (m+addX[i] >= 0 && m+addX[i] < grid.length && n+addY[i] >= 0 && n+addY[i] < grid[0].length) {
                if (grid[m+addX[i]][n+addY[i]] == 'O') {
                    return new Position(m+addX[i], n+addY[i], 1);
                }
                else if (grid[m+addX[i]][n+addY[i]] != '#') {
                    neib.add(new Position(m+addX[i], n+addY[i], 1));
                }
            }
        }
        while (!neib.isEmpty()) {
            Position temp = neib.get(0);
            m = temp.x;
            n = temp.y;
            //上->左->下->右
            for (int i = 0; i < addX.length; i++) {
                if (m+addX[i] >= 0 && m+addX[i] < grid.length && n+addY[i] >= 0 && n+addY[i] < grid[0].length) {
                    if (grid[m+addX[i]][n+addY[i]] == 'O') {
                        return new Position(m+addX[i], n+addY[i], temp.val+1);
                    }
                    else if (grid[m+addX[i]][n+addY[i]] != '#') {
                        neib.add(new Position(m+addX[i], n+addY[i], temp.val+1));
                    }
                }
            }
            neib.remove(0);
        }
        //没找到
        return (new Position(-1,-1, -1));
    }
}

class Position {
    int x;
    int y;
    int val;
    Position(int m, int n, int v) {
        x = m;
        y = n;
        val = v;
    }
}



/*
    struct Point{
        int x;
        int y;

        void print(){
        printf("%d %d\n", x, y);
        }
        };
*
class Solution {
    public:

    static const int maxn = 102;

    int dist[maxn][maxn], vis[maxn][maxn];
    Point q[maxn * maxn], S, T, puzzle[20], stone[50];

    int distS[50], distT[50], distM[20][50], distBM[20][20];

    int dp[1 << 16][16];

    int dx[4] = {1,0,-1,0};
    int dy[4] = {0,1,0,-1};

    void BFS(vector<string>& maze, int &n, int &m, Point start){
        int front, rear;
        memset(vis, 0, sizeof(vis));
        memset(dist, -1, sizeof(dist));

        front = rear = 1;

        dist[start.x][start.y] = 0;
        vis[start.x][start.y] = 1;
        q[rear++] = start;

        while(front != rear){
            Point i = q[front++];
            int nowx = i.x, nowy = i.y;

            for(int dir = 0; dir < 4; ++dir){
                int newx = i.x + dx[dir], newy = i.y + dy[dir];

                if(newx >= 0 && newx < n && newy >= 0 && newy < m){
                    if(maze[newx][newy] != '#' && !vis[newx][newy]){
                        dist[newx][newy] = dist[nowx][nowy] + 1;
                        q[rear++] = (Point){newx, newy};
                        vis[newx][newy] = 1;

                    }
                }
            }
        }
    }

    int minimalSteps(vector<string>& maze) {
        int n = maze.size(), m = maze[0].size();

        int curp, curs;
        curp = curs = 0;
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m; ++j){
                if(maze[i][j] == 'S')S = (Point){i, j};
                if(maze[i][j] == 'T')T = (Point){i, j};
                if(maze[i][j] == 'M')puzzle[curp++] = (Point){i, j};
                if(maze[i][j] == 'O')stone[curs++] = (Point){i, j};
            }

        memset(distS, -1, sizeof(distS));
        memset(distT, -1, sizeof(distT));
        memset(distM, -1, sizeof(distM));

        BFS(maze, n, m, S);

        if(curp == 0)return dist[T.x][T.y];

        for(int i = 0; i < curs; ++i){
            Point &sto = stone[i];
            distS[i] = dist[sto.x][sto.y];
            //printf("%d %d\n", i, distS[i]);
        }

        BFS(maze, n, m, T);

        for(int i = 0; i < curp; ++i){
            Point &puz = puzzle[i];
            distT[i] = dist[puz.x][puz.y];
            //printf("%d %d\n", i, distT[i]);
        }

        for(int i = 0; i < curp; ++i){
            Point puz = puzzle[i];
            BFS(maze, n, m, puz);
            for(int j = 0; j < curs; ++j){
                Point sto = stone[j];
                distM[i][j] = dist[sto.x][sto.y];
            }
        }


        memset(dp, -1, sizeof(dp));



        for(int i = 0; i < curp; i++){
            int idx = 1 << i, best = -1;
            for(int j = 0; j < curs; j++){
                if(distS[j] != -1 && distM[i][j] != -1){
                    int curDist = distS[j] + distM[i][j];
                    if(best == -1 || curDist < best)best = curDist;
                }
            }

            dp[idx][i] = best;

            //printf("%d %d %d\n", idx, i, best);
        }

        int ALL = 1 << curp;

        for(int i = 0; i < curp; ++i)
            for(int j = 0; j < curp; ++j){
                if(i == j)distBM[i][j] = 0;
                else{
                    distBM[i][j] = -1;
                    for(int k = 0; k < curs; ++k ){
                        if(distM[i][k] != -1 && distM[j][k] != -1){
                            int cur = distM[i][k] + distM[j][k];
                            if(distBM[i][j] == -1 || cur < distBM[i][j])
                                distBM[i][j] = cur;
                        }
                    }
                }
            }


        for(int i = 1; i < ALL; ++i){
            for(int j = 0; j < curp; ++j){
                if(i & (1 << j)){
                    for(int k = 0; k < curp; ++k){
                        if(j != k && (i & (1 << k)) && dp[i - (1 << j)][k] != -1 && distBM[k][j] != -1){

                            int cur = dp[i - (1 << j)][k] + distBM[k][j];
                            //printf("%d %d %d %d\n", i, j, k, cur);

                            if(dp[i][j] == -1 || cur < dp[i][j])dp[i][j] = cur;
                        }
                    }
                }
            }
        }


        int ans = -1;
        for(int i = 0; i < curp; ++i){
            if(dp[ALL - 1][i] != -1 && distT[i] != -1){
                int cur = dp[ALL - 1][i] + distT[i];
                if(ans == -1 || cur < ans)ans = cur;
            }
        }




        return ans;

    }
};


 */
