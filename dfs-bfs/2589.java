import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  2589. 보물섬
 *  최장 최단거리이므로 BFS 결과 중 가장 큰 값을 출력하면 된다.
 */
public class Main {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static char[][] map;
	static int N, M, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') 
					bfs(i, j);
			}
		}
		System.out.println(ans);
	}

	static void bfs(int x, int y) {
		boolean visit[][] = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visit[x][y] = true;
		int dist = -1;
		while (!q.isEmpty()) {
			int sz = q.size();
			for (int k = 0; k < sz; k++) {
				Point p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] || map[nx][ny] == 'W')
						continue;
					visit[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
			dist++;
		}
		ans = Math.max(dist, ans);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}