/*
class Solution {
	private static final int MAX_VALUE = Integer.MAX_VALUE/2;

	static class Point{
		int x;
		int y;
		int dis;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point setDis(int dis) {
			this.dis = dis;
			return this;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x &&
					y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	int[] sToMdis;
	int[][] mToMdis;
	int[] sToOdis;
	int[][] mToOdis;
	int[][] oToMdis;
	int[] mToTdis;
	Map<Point, Integer> pointToIndex = new HashMap<>();
	char[][] map;
	public int minimalSteps(String[] maze) {
		map = new char[maze.length+2][maze[0].length()+2];
		for(char[] row : map) {
			Arrays.fill(row, '#');
		}
		int oLen = 0;
		int mLen = 0;
		Point start = null;
		Point end = null;
		List<Point> oList = new ArrayList<>();
		List<Point> mList = new ArrayList<>();
		for(int y = 0;y<maze.length;y++) {
			for(int x = 0; x < maze[0].length();x++) {
				map[y+1][x+1] = maze[y].charAt(x);
				Point p = new Point(x+1, y+1);
				if (map[y+1][x+1] == 'O') {
					oList.add(p);
					pointToIndex.put(p, oLen++);
				} else if (map[y+1][x+1] == 'M') {
					mList.add(p);
					pointToIndex.put(p, mLen++);
				}else if(map[y+1][x+1] == 'S') {
					pointToIndex.put(p, 0);
					start = new Point(x+1,y+1);
				}else if (map[y+1][x+1] == 'T') {
					pointToIndex.put(p,0);
					end = p;
				}
			}
		}

		Map<Integer,Integer> sToTMap = bfs(start.x, start.y, 'T');
		// 如果s到不了T，直接返回-1
		if (sToTMap.isEmpty()) {
			return -1;
		}

		// 如果M的个数为0，那么直接返回S到T的距离
		if (mLen == 0) {
			return sToTMap.get(0);
		}

		// 计算s到所有O的距离
		sToOdis = new int[oLen];
		Arrays.fill(sToOdis, MAX_VALUE);
		Map<Integer,Integer> sToOMap = bfs(start.x, start.y, 'O');
		// 如果s连1个O都到不了，肯定不行。
		if (sToOMap.isEmpty()) {
			return -1;
		}
		for (Map.Entry<Integer, Integer> entry : sToOMap.entrySet()) {
			sToOdis[entry.getKey()] = entry.getValue();
		}

		// 如果s到不了所有M，则也不行
		Map<Integer,Integer> sToMMap = bfs(start.x, start.y, 'M');
		if (sToMMap.size() < mLen) {
			return -1;
		}

		// 计算所有o到M的距离
		oToMdis = new int[oLen][mLen];
		for (int[] oom : oToMdis) {
			Arrays.fill(oom, MAX_VALUE);
		}
		int i = 0;
		for (Point op : oList) {
			Map<Integer,Integer> oToMMap = bfs(op.x, op.y, 'M');
			if (oToMMap.isEmpty()) {
				i++;
				continue;
			}
			for (Map.Entry<Integer, Integer> entry : oToMMap.entrySet()) {
				oToMdis[i][entry.getKey()] = entry.getValue();
			}
			i++;
		}

		mToOdis = new int[mLen][oLen];
		mToTdis = new int[mLen];
		i = 0;
		// 计算所有m到O的距离，和所有m到T的距离
		for (Point mp : mList) {
			Map<Integer,Integer> mToOMap = bfs(mp.x, mp.y, 'O');
			// 有一个M找不到任何和O的路径，那么就是错误的
			if (mToOMap.isEmpty()) {
				return -1;
			}
			for (Map.Entry<Integer, Integer> entry : mToOMap.entrySet()) {
				mToOdis[i][entry.getKey()] = entry.getValue();
			}
			// 有一个找不到T，那也是错误的
			Map<Integer,Integer> mToTMap = bfs(mp.x, mp.y, 'T');
			if (mToTMap.isEmpty()) {
				return -1;
			}
			mToTdis[i] = mToTMap.get(0);
			i++;
		}
		sToMdis = new int[mLen];
		Arrays.fill(sToMdis, MAX_VALUE);
		mToMdis = new int[mLen][mLen];
		for (int[] mmm : mToMdis) {
			Arrays.fill(mmm, MAX_VALUE);
		}

		for (int m = 0; m < mLen;m++) {
			for (int o = 0; o < oLen;o++) {
				// 计算s到各m且至少经过1个O的最小距离
				int dis = sToOdis[o] + oToMdis[o][m];
				if (dis < sToMdis[m]) {
					sToMdis[m] = dis;
				}

				// 计算m到各mEnd且至少经过1个O的最小距离
				for (int mEnd = 0;mEnd<mLen;mEnd++) {
					int mmDis = mToOdis[m][o] + oToMdis[o][mEnd];
					if (mmDis < mToMdis[m][mEnd]) {
						mToMdis[m][mEnd] = mmDis;
					}
				}
			}
		}
		int mStatu = (1 << mLen);
		int[][][] dp = new int[mLen+1][mStatu][mLen];
		int bigStatu = mStatu - 1;
		for (int m = 0; m < mLen;m++) {
			dp[mLen][bigStatu][m] = mToTdis[m];
		}
		// dp指该状态下该位置的m到终点的最短距离
		// 对于那个m他要选1个m
		for (int mCount = mLen-1;mCount>=1;mCount--) {
			for(int statu = bigStatu; statu>=0;statu--) {
				for (int m = 0; m < mLen;m++) {
					int minDis = MAX_VALUE;
					for(int endM = 0;endM < mLen;endM++) {
						// endM必须是0
						if ((statu & (1<<endM)) != 0 || m == endM) {
							continue;
						}
						int realDis = mToMdis[m][endM] + dp[mCount+1][statu | (1<<endM)][endM];
						if (realDis < minDis) {
							minDis = realDis;
						}
					}
					dp[mCount][statu][m] = minDis;
				}
			}
		}

		int result = MAX_VALUE;
		for (int m = 0; m < mLen;m++) {
			int sToMToTDis = sToMdis[m] + dp[1][1<<m][m];
			if (sToMToTDis < result) {
				result = sToMToTDis;
			}
		}
		return result == MAX_VALUE?-1:result;
	}

	Map<Integer, Integer> bfs(int x, int y, char findChar) {
		boolean[][] vis = new boolean[map.length][map[0].length];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		vis[y][x] = true;
		Map<Integer, Integer> indexAndDisMap = new HashMap<>();
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int px = p.x;
			int py = p.y;
			if (map[py][px] == findChar) {
				indexAndDisMap.put(pointToIndex.get(p), p.dis);
			}
			deal(px+1,py,queue,vis, p.dis + 1);
			deal(px-1,py,queue,vis, p.dis + 1);
			deal(px,py-1,queue,vis, p.dis + 1);
			deal(px,py+1,queue,vis, p.dis + 1);
		}
		return indexAndDisMap;
	}

	void deal(int x,int y, Queue<Point> queue, boolean[][] vis, int dis) {
		if (!vis[y][x] && map[y][x] != '#') {
			vis[y][x] = true;
			queue.offer(new Point(x,y).setDis(dis));
		}
	}
}

 */